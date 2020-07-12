package com.example.around.ui.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.around.ui.factories.MainActViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient

object Injection {

  /**
   * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
   * [ViewModel] objects.
   */
  fun provideMainActViewModelFactory(
    application: Application,
    fusedLocationProviderClient: FusedLocationProviderClient
  ): ViewModelProvider.Factory {
    return MainActViewModelFactory(application, fusedLocationProviderClient)
  }

}