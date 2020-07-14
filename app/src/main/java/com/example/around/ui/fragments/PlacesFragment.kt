package com.example.around.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.around.R
import com.example.around.databinding.FragmentPlacesBinding
import com.example.around.ui.adapters.SearchResultListAdapter
import com.example.around.ui.utils.onClickKeyboardDoneButton
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
      }
    })

    val listAdapter = SearchResultListAdapter()
    binding.recyclerViewPlaces.adapter = listAdapter

    viewModel.searchResults.observe(viewLifecycleOwner, Observer {
      listAdapter.submitList(it)
    })

    val editText = binding.editTextCouponSearch
    editText.onClickKeyboardDoneButton {
      val query = binding.editTextCouponSearch.text.toString()
      viewModel.startPlacesSearch(query)

      binding.editTextCouponSearch.clearFocus()
      view?.let {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it.windowToken, 0)
      }
    }
    editText.setOnFocusChangeListener { view, hasFocus ->
      if (hasFocus) {
        binding.editTextCouponSearch.hint = ""
      } else {
        binding.editTextCouponSearch.hint = getString(R.string.hint_search)
      }
    }

    binding.viewModel = viewModel
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