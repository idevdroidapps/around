package com.example.around.data.database

import androidx.room.*
import com.example.around.data.models.NearbySearch

@Dao
interface SearchesDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(search: NearbySearch): Long

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  @JvmSuppressWildcards
  fun insertSearches(searches: List<NearbySearch>)

  @Update
  fun update(search: NearbySearch)

  @Delete
  fun deleteSearch(search: NearbySearch)

  @Query("DELETE FROM searches_table")
  fun clearSearches()

}