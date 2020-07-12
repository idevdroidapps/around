package com.example.around.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.around.R
import com.example.around.databinding.FragmentPlacesBinding
import com.example.around.ui.viewmodels.SharedViewModel

class PlacesFragment : Fragment() {

  private val viewModel: SharedViewModel by activityViewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val binding: FragmentPlacesBinding =
      DataBindingUtil.inflate(inflater, R.layout.fragment_places, container, false)

    viewModel.locationPermission.observe(viewLifecycleOwner, Observer { hasPermission ->
      if (!hasPermission) {
        providePermissionRationale()
      } else{
        viewModel.searchNearbyPlaces()
      }
    })

    binding.viewModel = viewModel
    binding.lifecycleOwner = this
    return binding.root
  }

  private fun providePermissionRationale() {
    activity?.let {
      PermDialogFragment().show(
        it.supportFragmentManager,
        PermDialogFragment::class.java.simpleName
      )
    }
  }

}