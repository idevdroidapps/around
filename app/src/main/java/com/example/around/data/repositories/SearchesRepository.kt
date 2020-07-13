package com.example.around.data.repositories

import com.example.around.data.database.SearchesDao
import com.example.around.data.models.NearbySearch

class SearchesRepository private constructor(private val searchesDao: SearchesDao) {

  suspend fun insertSearches(searches: List<NearbySearch>) {
    searchesDao.insertSearches(searches)
  }

  companion object {
    // For Singleton instantiation
    @Volatile
    private var instance: SearchesRepository? = null

    fun getInstance(searchesDao: SearchesDao) =
      instance ?: synchronized(this) {
        instance ?: SearchesRepository(searchesDao).also { instance = it }
      }
  }
}