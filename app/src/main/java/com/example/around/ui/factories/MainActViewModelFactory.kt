package com.example.around.ui.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.around.ui.viewmodels.SharedViewModel
import com.google.android.gms.location.FusedLocationProviderClient

class MainActViewModelFactory(
    private val application: Application,
    private val fusedLocationProviderClient: FusedLocationProviderClient
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel(application, fusedLocationProviderClient) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}