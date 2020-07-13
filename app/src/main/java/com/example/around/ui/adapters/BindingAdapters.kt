package com.example.around.ui.adapters

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.around.R
import com.example.around.data.models.SearchResult


@BindingAdapter("placeImage")
fun ImageView.setPlaceImage(place: SearchResult?) {
  place?.let {
    val options = RequestOptions()
      .diskCacheStrategy(DiskCacheStrategy.NONE)
      .override(200, 150)
      .fitCenter()
    try {
      val baseurl = "https://maps.googleapis.com/maps/api/place/photo?"
      val maxwidth = "maxwidth=400"
      val key= "&key=${this.context.resources.getString(R.string.maps_api_key)}"
      val photoreference = "&photoreference=${it.image}"
      val requestUri = Uri.parse("$baseurl$maxwidth$photoreference$key")
      Glide
        .with(this.context)
        .load(requestUri)
        .apply(options)
        .into(this)
    } catch (e: Exception) {
      Log.e("Glide","Place Image Failed in Glide")
    }
  }
}

