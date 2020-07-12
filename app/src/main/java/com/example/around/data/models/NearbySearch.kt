package com.example.around.data.models

import androidx.room.*

@Entity(tableName = "searches_table")
data class NearbySearch (
  @PrimaryKey @ColumnInfo(name = "query") val query: String,
  val results: List<SearchResult>
)