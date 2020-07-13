package com.example.around.ui.adapters

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.around.data.models.NearbySearchWithPlaces
import com.example.around.ui.viewholders.NearbySearchViewHolder
import com.example.around.ui.viewholders.SearchResultViewHolder

class SearchResultListAdapter : PagedListAdapter<NearbySearchWithPlaces, RecyclerView.ViewHolder>(DiffCallback) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return NearbySearchViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    getItem(position)?.let {
      (holder as NearbySearchViewHolder).bind(it.search)
    }
  }

  companion object DiffCallback : DiffUtil.ItemCallback<NearbySearchWithPlaces>() {
    override fun areItemsTheSame(oldItem: NearbySearchWithPlaces, newItem: NearbySearchWithPlaces): Boolean {
      return oldItem.search.id === newItem.search.id
    }

    override fun areContentsTheSame(oldItem: NearbySearchWithPlaces, newItem: NearbySearchWithPlaces): Boolean {
      var same = true
      oldItem.places.forEachIndexed { index, searchResult ->
        if(searchResult.id != newItem.places[index].id) same = false
      }
      return same
    }
  }
}