package com.example.around.ui.adapters

import android.location.Location
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("location")
fun TextView.setLocation(location: Location?) {
  location?.let {
    val lat = it.latitude
    val long = it.longitude
    val txtLoc = "Lat: $lat, Long: $long"
    text = txtLoc
  }
}