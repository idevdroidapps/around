package com.example.around.ui.adapters

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.around.data.models.NearbySearchWithPlaces
import com.example.around.ui.viewholders.SearchResultViewHolder

class SearchResultListAdapter : PagedListAdapter<NearbySearchWithPlaces, SearchResultViewHolder>(DiffCallback) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
    return SearchResultViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
    getItem(position)?.let {
      holder.bind(it.places[position])
    }
  }

  companion object DiffCallback : DiffUtil.ItemCallback<NearbySearchWithPlaces>() {
    override fun areItemsTheSame(oldItem: NearbySearchWithPlaces, newItem: NearbySearchWithPlaces): Boolean {
      return oldItem.search.id === newItem.search.id
    }

    override fun areContentsTheSame(oldItem: NearbySearchWithPlaces, newItem: NearbySearchWithPlaces): Boolean {
      return oldItem == newItem
    }
  }
}