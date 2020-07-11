package com.example.around.ui.viewmodels

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.around.data.utils.Constants.PERMISSIONS_LOCATION_REQUEST_CODE

class MainActViewModel(application: Application) : AndroidViewModel(application) {

  private val app = application

  private var _locationPermission = MutableLiveData<Boolean>()
  val locationPermission: LiveData<Boolean> get() = _locationPermission

  init {
    getLocationPermission()
  }

  fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    when (requestCode) {
      PERMISSIONS_LOCATION_REQUEST_CODE -> {
        _locationPermission.value = grantResults.isNotEmpty() &&
          grantResults[0] == PackageManager.PERMISSION_GRANTED
      }
    }
  }

  private fun getLocationPermission() {
    if (ContextCompat.checkSelfPermission(app.applicationContext,
        Manifest.permission.ACCESS_FINE_LOCATION)
      == PackageManager.PERMISSION_GRANTED) {
      setLocationPermission(true)
    } else {
      setLocationPermission(false)
    }
  }

  private fun setLocationPermission(hasPermission: Boolean) {
    _locationPermission.value = hasPermission
  }

}