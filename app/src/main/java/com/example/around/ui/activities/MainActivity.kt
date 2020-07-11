package com.example.around.ui.activities

import android.Manifest
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.around.R
import com.example.around.data.utils.Constants.PERMISSIONS_LOCATION_REQUEST_CODE
import com.example.around.databinding.ActivityMainBinding
import com.example.around.ui.di.Injection
import com.example.around.ui.fragments.PermDialogFragment
import com.example.around.ui.viewmodels.MainActViewModel

class MainActivity : AppCompatActivity(), DialogInterface.OnClickListener {

  private var viewModel: MainActViewModel? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding: ActivityMainBinding = DataBindingUtil
      .setContentView(this@MainActivity, R.layout.activity_main)

    viewModel = ViewModelProvider(
      this@MainActivity,
      Injection.provideMainActViewModelFactory(application)
    ).get(MainActViewModel::class.java)

    binding.viewModel = viewModel

    viewModel?.let {
      it.locationPermission.observe(this@MainActivity, Observer { hasPermission ->
        if (!hasPermission) {
          providePermissionRationale()
        }
      })
    }

  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    viewModel?.onRequestPermissionsResult(requestCode, permissions, grantResults)
  }

  private fun providePermissionRationale() {
    PermDialogFragment().show(supportFragmentManager, PermDialogFragment::class.java.simpleName)
  }

  override fun onClick(dialog: DialogInterface?, which: Int) {
    when(which){
      DialogInterface.BUTTON_POSITIVE -> {
          ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
          PERMISSIONS_LOCATION_REQUEST_CODE)
      }
      DialogInterface.BUTTON_NEGATIVE -> {
        dialog?.dismiss()
      }
    }
  }

}