package com.pdp.imperative.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class TVShowPopular(
    val total: String,
    val page: Int,
    val pages: Int,
    val tv_shows: ArrayList<TVShow>
) {
}

@Entity(tableName = "tv_show")
data class TVShow(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "start_date") val start_date: String? = null,
    @ColumnInfo(name = "end_date") val end_date: String? = null,
    @ColumnInfo(name = "network") val network: String? = null,
    @ColumnInfo(name = "status") val status: String? = null,
    @ColumnInfo(name = "image_thumbnail_path") val image_thumbnail_path: String? = null
)
