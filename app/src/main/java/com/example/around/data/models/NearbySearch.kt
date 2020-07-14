package com.example.around.data.models

import androidx.room.*

@Entity
data class NearbySearch(
    @PrimaryKey @ColumnInfo(name = "queryId") val id: String,
    @ColumnInfo(name = "miles") val miles: Int
)