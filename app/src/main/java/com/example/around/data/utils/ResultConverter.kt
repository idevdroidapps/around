package com.example.around.data.utils

import androidx.room.TypeConverter
import com.example.around.data.models.SearchResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ResultConverter {
  @TypeConverter
  fun fromSearchResultList(value: List<SearchResult>): String {
    val gson = Gson()
    val type = object : TypeToken<List<SearchResult>>() {}.type
    return gson.toJson(value, type)
  }

  @TypeConverter
  fun toCountryLangList(value: String): List<SearchResult> {
    val gson = Gson()
    val type = object : TypeToken<List<SearchResult>>() {}.type
    return gson.fromJson(value, type)
  }
}