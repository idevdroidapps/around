package com.example.around.ui.adapters

import android.location.Location
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("lastLocation")
fun TextView.setLastLocation(location: Location?) {
  location?.let {
    val lat = it.latitude.toString()
    val long = it.longitude.toString()
    val txtLoc = "Lat: $lat, Long: $long"
    text = txtLoc
  }
}