package com.example.around.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchResult(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "rating") var rating: Float,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "searchId") var searchId: Long
)