package com.example.around.ui.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.around.data.database.SearchesDao
import com.example.around.data.database.SearchesDatabase
import com.example.around.data.repositories.SearchesRepository
import com.example.around.ui.factories.SharedViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient

object Injection {

  /**
   * Creates an instance of [SearchesDao]
   */
  private fun provideSearchesDao(application: Application): SearchesDao {
    return SearchesDatabase.getInstance(application).searchesDao
  }

  /**
   * Creates an instance of [SearchesRepository]
   */
  private fun provideSearchesRepo(application: Application): SearchesRepository {
    return SearchesRepository.getInstance(provideSearchesDao(application))
  }

  /**
   * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
   * [ViewModel] objects.
   */
  fun provideMainActViewModelFactory(
    application: Application,
    fusedLocationProviderClient: FusedLocationProviderClient
  ): ViewModelProvider.Factory {
    return SharedViewModelFactory(application, fusedLocationProviderClient, provideSearchesRepo(application))
  }

}