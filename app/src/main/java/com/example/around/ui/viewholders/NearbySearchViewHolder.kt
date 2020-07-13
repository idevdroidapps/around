package com.example.around.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.around.data.models.NearbySearch
import com.example.around.databinding.ItemSearchBinding

class NearbySearchViewHolder(private val binding: ItemSearchBinding) :
  RecyclerView.ViewHolder(binding.root) {

  fun bind(nearbySearch: NearbySearch) {
    binding.search = nearbySearch
    binding.executePendingBindings()
  }

  companion object {
    fun from(parent: ViewGroup): NearbySearchViewHolder {
      val layoutInflater =
        LayoutInflater.from(parent.context)
      val binding = ItemSearchBinding.inflate(layoutInflater, parent, false)
      return NearbySearchViewHolder(binding)
    }
  }
}