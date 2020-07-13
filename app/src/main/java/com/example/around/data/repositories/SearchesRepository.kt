package com.example.around.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.around.data.database.SearchesDao
import com.example.around.data.models.NearbySearch
import com.example.around.data.models.NearbySearchWithPlaces
import com.example.around.data.models.SearchResult
import com.example.around.data.utils.Constants.DATABASE_PAGE_SIZE

class SearchesRepository private constructor(private val searchesDao: SearchesDao) {

  suspend fun insertSearches(searches: List<NearbySearch>) {
    searchesDao.insertSearches(searches)
  }

  suspend fun insertSearch(search: NearbySearch) {
    searchesDao.insertSearch(search)
  }

  suspend fun insertPlaces(places: List<SearchResult>) {
    searchesDao.insertPlaces(places)
  }

  suspend fun insertPlace(place: SearchResult) {
    searchesDao.insertPlace(place)
  }

  fun getSearches(): LiveData<PagedList<NearbySearchWithPlaces>> {
    val dataSourceFactory = searchesDao.getNearbySearchWithPlaces()
    return LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE).build()
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