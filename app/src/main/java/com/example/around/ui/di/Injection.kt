package com.example.around.ui.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.around.ui.factories.MainActViewModelFactory

object Injection {

  /**
   * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
   * [ViewModel] objects.
   */
  fun provideMainActViewModelFactory(application: Application): ViewModelProvider.Factory {
    return MainActViewModelFactory(application)
  }

}