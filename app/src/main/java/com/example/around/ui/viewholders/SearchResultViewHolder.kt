package com.example.around.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.around.data.models.NearbySearchWithPlaces
import com.example.around.data.models.SearchResult
import com.example.around.databinding.ItemPlaceBinding

class SearchResultViewHolder(private val binding: ItemPlaceBinding) :
  RecyclerView.ViewHolder(binding.root) {

  fun bind(searchResult: SearchResult) {
    binding.place = searchResult
    binding.executePendingBindings()
  }

  companion object {
    fun from(parent: ViewGroup): SearchResultViewHolder {
      val layoutInflater =
        LayoutInflater.from(parent.context)
      val binding = ItemPlaceBinding.inflate(layoutInflater, parent, false)
      return SearchResultViewHolder(binding)
    }
  }
}