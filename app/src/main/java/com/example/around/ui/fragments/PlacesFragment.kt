package com.example.around.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast
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

    binding.imageViewSearchButton.setOnClickListener {
      startSearch(binding)
    }

    initEditText(binding)
    initSeekBar(binding)

    activity?.let {
      val historyImageVIew = it.findViewById<ImageView>(R.id.imageView_history)
      historyImageVIew?.setOnClickListener {
        Toast.makeText(context, "History", Toast.LENGTH_SHORT).show()
      }
    }

    binding.viewModel = viewModel
    return binding.root
  }

  private fun initEditText(binding: FragmentPlacesBinding) {
    val editText = binding.editTextCouponSearch
    editText.onClickKeyboardDoneButton {
      startSearch(binding)
    }
    editText.setOnFocusChangeListener { view, hasFocus ->
      if (hasFocus) {
        binding.editTextCouponSearch.hint = ""
      } else {
        binding.editTextCouponSearch.hint = getString(R.string.hint_search)
      }
    }
  }

  private fun initSeekBar(binding: FragmentPlacesBinding) {
    val defaultHeaderTxt = "${resources.getInteger(R.integer.default_distance_miles)} miles"
    binding.textViewDistanceHeader.text = defaultHeaderTxt
    binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
      override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        val progressText = "$progress miles"
        binding.textViewDistanceHeader.text = progressText
      }

      override fun onStartTrackingTouch(seekBar: SeekBar?) {

      }

      override fun onStopTrackingTouch(seekBar: SeekBar?) {

      }

    })
  }

  private fun providePermissionRationale() {
    activity?.let {
      PermDialogFragment().show(
        it.supportFragmentManager,
        PermDialogFragment::class.java.simpleName
      )
    }
  }

  private fun startSearch(binding: FragmentPlacesBinding) {
    binding.editTextCouponSearch.clearFocus()
    view?.let {
      val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(it.windowToken, 0)
    }

    val query = binding.editTextCouponSearch.text.toString()
    viewModel.startPlacesSearch(query, binding.seekBar.progress)
  }

}