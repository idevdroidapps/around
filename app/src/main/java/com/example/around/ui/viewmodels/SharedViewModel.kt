package com.example.around.ui.viewmodels

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.around.R
import com.example.around.data.utils.Constants.PERMISSIONS_LOCATION_REQUEST_CODE
import com.google.android.gms.location.FusedLocationProviderClient

class SharedViewModel(
  application: Application,
  private val fusedLocationProviderClient: FusedLocationProviderClient
) : AndroidViewModel(application) {
  private val app = application

  private var _locationPermission = MutableLiveData<Boolean>()
  val locationPermission: LiveData<Boolean> get() = _locationPermission

  private var _lastLocation = MutableLiveData<Location>()
  val lastLocation: LiveData<Location> get() = _lastLocation

  fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    when (requestCode) {
      PERMISSIONS_LOCATION_REQUEST_CODE -> {
        _locationPermission.value = grantResults.isNotEmpty() &&
          grantResults[0] == PackageManager.PERMISSION_GRANTED
      }
    }
  }

  fun searchNearbyPlaces() {
    if (hasPermission()) {
      try {
        val locationResult = fusedLocationProviderClient.lastLocation
        locationResult.addOnCompleteListener {
          if (it.isSuccessful) {
            val result = it.result
            if(result != null){
              Log.d("LOCATION",it.result?.latitude.toString() + ", " + it.result?.longitude.toString())
              _lastLocation.value = it.result
            } else {
              showErrorToast()
            }
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