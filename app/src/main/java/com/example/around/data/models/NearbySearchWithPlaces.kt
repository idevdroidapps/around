package com.example.around.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class NearbySearchWithPlaces (
    @Embedded val search: NearbySearch,
    @Relation(
        parentColumn = "queryId",
        entityColumn = "searchId"
    ) val places: List<SearchResult>
)