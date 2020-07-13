package com.example.around.data.models

import androidx.room.*

@Entity
data class NearbySearch(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "queryId") val id: Long,
    @ColumnInfo(name = "query") val query: String
)