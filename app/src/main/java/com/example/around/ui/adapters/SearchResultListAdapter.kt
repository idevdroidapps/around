package com.example.around.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.around.data.models.SearchResult
import com.example.around.ui.viewholders.SearchResultViewHolder

class SearchResultListAdapter : ListAdapter<SearchResult, SearchResultViewHolder>(DiffCallback) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
    return SearchResultViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
    getItem(position)?.let {
      holder.bind(it)
    }
  }

  companion object DiffCallback : DiffUtil.ItemCallback<SearchResult>() {
    override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
      return oldItem.name === newItem.name
    }

    override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
      return oldItem == newItem
    }
  }
}