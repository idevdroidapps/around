package com.example.around.ui.adapters

import android.location.Location
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapters {

  @BindingAdapter("app:lastLocation")
  @JvmStatic
  fun lastLocation(view: TextView, location: Location?) {
    location?.let {
      val lat = it.latitude.toString()
      val long = it.longitude.toString()
      val txtLoc = "Current Location: $lat,  $long"
      view.text = txtLoc
    }
  }

}