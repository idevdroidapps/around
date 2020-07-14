package com.example.around.data.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.around.data.models.NearbySearch
import com.example.around.data.models.NearbySearchWithPlaces
import com.example.around.data.models.SearchResult

@Dao
interface SearchesDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertSearch(search: NearbySearch): Long

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  @JvmSuppressWildcards
  suspend fun insertSearches(searches: List<NearbySearch>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPlace(place: SearchResult): Long

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  @JvmSuppressWildcards
  suspend fun insertPlaces(places: List<SearchResult>)

  @Update
  fun update(search: NearbySearch)

  @Delete
  fun deleteSearch(search: NearbySearch)

  @Transaction
  @Query("DELETE FROM NearbySearch")
  fun clearSearchHistory()

  @Transaction
  @Query("SELECT * FROM NearbySearch")
  fun getNearbySearchWithPlaces(): DataSource.Factory<Int, NearbySearchWithPlaces>

  @Query("SELECT * FROM NearbySearch")
  fun getNearbySearches(): LiveData<List<NearbySearch>>

}