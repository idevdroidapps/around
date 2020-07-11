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
import com.example.around.data.utils.Constants.PERMISSIONS_LOCATION_REQUEST_CODE

class SharedViewModel(application: Application) : AndroidViewModel(application) {

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
      Toast.makeText(app, "SEARCH STARTED", Toast.LENGTH_SHORT).show()
    } else {
      _locationPermission.value = false
    }
  }

  private fun hasPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
      app.applicationContext,
      Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
  }

}