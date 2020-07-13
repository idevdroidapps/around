package com.example.around.ui.activities

import android.Manifest
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.around.R
import com.example.around.data.utils.Constants
import com.example.around.databinding.ActivityMainBinding
import com.example.around.ui.di.Injection
import com.example.around.ui.viewmodels.SharedViewModel
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity(), DialogInterface.OnClickListener {

  private var viewModel: SharedViewModel? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding: ActivityMainBinding = DataBindingUtil
      .setContentView(this@MainActivity, R.layout.activity_main)

    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

    viewModel = ViewModelProvider(
      this@MainActivity,
      Injection.provideMainActViewModelFactory(
        application,
        fusedLocationProviderClient
      )
    ).get(SharedViewModel::class.java)

    binding.viewModel = viewModel
    binding.lifecycleOwner = this

  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    viewModel?.onRequestPermissionsResult(requestCode, grantResults)
  }

  override fun onClick(dialog: DialogInterface?, which: Int) {
    when (which) {
      DialogInterface.BUTTON_POSITIVE -> {
        ActivityCompat.requestPermissions(
          this@MainActivity,
          arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
          Constants.PERMISSIONS_LOCATION_REQUEST_CODE
        )
      }
      DialogInterface.BUTTON_NEGATIVE -> {
        dialog?.dismiss()
      }
    }
  }


}