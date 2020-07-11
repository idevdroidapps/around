package com.example.around.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.around.R
import com.example.around.databinding.ActivityMainBinding
import com.example.around.ui.di.Injection
import com.example.around.ui.viewmodels.MainActViewModel
import com.google.android.gms.location.FusedLocationProviderClient

class MainActivity : AppCompatActivity() {

  private var binding: ActivityMainBinding? = null
  private var viewModel: MainActViewModel? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

    viewModel = ViewModelProvider(this@MainActivity, Injection.provideMainActViewModelFactory(application))
      .get(MainActViewModel::class.java)

    binding?.let {
      it.viewModel = viewModel
    }

  }
}