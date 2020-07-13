package com.example.around.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.around.data.models.NearbySearch
import com.example.around.data.models.SearchResult

@Database(entities = [NearbySearch::class, SearchResult::class], version = 1, exportSchema = false)
abstract class SearchesDatabase: RoomDatabase() {

  abstract val searchesDao: SearchesDao

  companion object{

    @Volatile
    private var INSTANCE: SearchesDatabase? = null

    fun getInstance(context: Context): SearchesDatabase {
      return INSTANCE ?: synchronized(this) {
        INSTANCE ?: buildRoomDatabase(context).also { INSTANCE = it }
      }
    }

    private fun buildRoomDatabase(context: Context): SearchesDatabase {
      return Room.databaseBuilder(context, SearchesDatabase::class.java, "searches_database")
        .build()
    }

  }

}