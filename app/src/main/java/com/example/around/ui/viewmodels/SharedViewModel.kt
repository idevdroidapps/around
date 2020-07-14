package com.example.around.ui.viewmodels

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.example.around.R
import com.example.around.data.models.NearbySearch
import com.example.around.data.models.NearbySearchWithPlaces
import com.example.around.data.models.SearchResult
import com.example.around.data.repositories.SearchesRepository
import com.example.around.data.utils.Constants.PERMISSIONS_LOCATION_REQUEST_CODE
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.maps.GeoApiContext
import com.google.maps.PlacesApi
import com.google.maps.model.LatLng
import com.google.maps.model.PlaceType
import com.google.maps.model.PlacesSearchResponse
import com.google.maps.model.RankBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SharedViewModel(
  application: Application,
  private val fusedLocationProviderClient: FusedLocationProviderClient,
  private val searchesRepository: SearchesRepository
) : AndroidViewModel(application) {
  private val app = application

  private var _locationPermission = MutableLiveData<Boolean>()
  val locationPermission: LiveData<Boolean> get() = _locationPermission

  private var _lastLocation = MutableLiveData<Location>()
  val lastLocation: LiveData<Location> get() = _lastLocation

  private var _searchResults = MutableLiveData<List<SearchResult>>()
  val searchResults: LiveData<List<SearchResult>> get() = _searchResults

  fun onRequestPermissionsResult(
    requestCode: Int,
    grantResults: IntArray
  ) {
    when (requestCode) {
      PERMISSIONS_LOCATION_REQUEST_CODE -> {
        _locationPermission.value = grantResults.isNotEmpty() &&
          grantResults[0] == PackageManager.PERMISSION_GRANTED
      }
    }
  }

  fun startPlacesSearch(query: String) {
    if (hasPermission()) {
      try {
        val locationResult = fusedLocationProviderClient.lastLocation
        locationResult.addOnCompleteListener {
          if (it.isSuccessful && it.result != null) {
            _lastLocation.value = it.result
            fetchPlaces(query)
          } else {
            showErrorToast()
          }
        }
      } catch (e: SecurityException) {
        _locationPermission.value = false
      }
    } else {
      _locationPermission.value = false
    }
  }

  private fun fetchPlaces(query: String) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        var searchResponse = PlacesSearchResponse()
        withContext(Dispatchers.IO) {
          searchResponse = conductNearbyQuery(query)
        }
        val results = searchResponse.results
        val placesList = ArrayList<SearchResult>()
        results?.let { searchResults ->
          viewModelScope.launch {
            val iter = searchResults.iterator()
            var iterCount = 0
            withContext(Dispatchers.IO) {
              searchesRepository.insertSearch(NearbySearch(query))
              while (iter.hasNext() && iterCount < 10) {
                val place = iter.next()
                placesList.add(SearchResult(
                  place.placeId,
                  place.name,
                  place.rating,
                  place.photos?.first()?.photoReference,
                  query)
                )
                iterCount++
              }
              searchesRepository.insertPlaces(placesList)
            }
            _searchResults.value = placesList
          }
        }
      }
    }
  }

  private suspend fun conductNearbyQuery(query: String): PlacesSearchResponse =
    suspendCoroutine { continuation ->
      _lastLocation.value?.let { location ->
        try {
          val geoApiContext =
            GeoApiContext.Builder().apiKey(app.getString(R.string.maps_api_key)).build()
          PlacesApi.nearbySearchQuery(geoApiContext, LatLng(location.latitude, location.longitude))
            .radius(5000)
            .rankby(RankBy.PROMINENCE)
            .keyword(query)
            .language("en")
            .type(PlaceType.RESTAURANT)
            .setCallback(object : com.google.maps.PendingResult.Callback<PlacesSearchResponse> {
              override fun onFailure(e: Throwable?) {
                e?.let {
                  continuation.resumeWithException(it)
                }
              }

              override fun onResult(result: PlacesSearchResponse?) {
                result?.let {
                  continuation.resume(it)
                }
              }
            })
        } catch (e: IllegalStateException) {
          GlobalScope.launch {
            withContext(Dispatchers.Main) {
              Toast.makeText(app, e.message, Toast.LENGTH_LONG).show()
            }
          }
        }
      }

    }

  private fun showErrorToast() {
    Toast.makeText(app, app.getString(R.string.err_msg_no_location), Toast.LENGTH_SHORT)
      .show()
  }

  private fun hasPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
      app.applicationContext,
      Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
  }

}