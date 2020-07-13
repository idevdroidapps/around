package com.example.around.data.database

import androidx.room.*
import com.example.around.data.models.NearbySearch
import com.example.around.data.models.NearbySearchWithPlaces

@Dao
interface SearchesDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(search: NearbySearch): Long

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertSearches(searches: List<NearbySearch>)

  @Update
  suspend fun update(search: NearbySearch)

  @Delete
  suspend fun deleteSearch(search: NearbySearch)

  @Transaction
  @Query("DELETE FROM NearbySearch")
  suspend fun clearSearches()

  @Transaction
  @Query("SELECT * FROM NearbySearch")
  suspend fun getPreviousSearches(): List<NearbySearchWithPlaces>

}