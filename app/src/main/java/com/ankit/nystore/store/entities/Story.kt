package com.ankit.nystore.store.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.ankit.nystore.store.entities.Story.Companion.TABLE_NAME


@Entity(tableName = TABLE_NAME)
data class Story(
    @ColumnInfo(name = "byline") var byline: String? = null,
    @ColumnInfo(name = "created_date") var created_date: String? = null,
    @ColumnInfo(name = "item_type") var item_type: String? = null,
    @ColumnInfo(name = "published_date") var published_date: String? = null,
    @ColumnInfo(name = "updated_date") var updated_date: String? = null,
    @ColumnInfo(name = "section") var section: String? = null,
    @ColumnInfo(name = "short_url") var short_url: String? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "abstract") var abstract: String? = null,
    @ColumnInfo(name = "multimedia") var multimedia: ArrayList<MultiMedia>? = null,
    @ColumnInfo(name = "url") var url: String? = null) {
  
  
  
  @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int? = null
  
  companion object {
    internal const val TABLE_NAME = "Details"
  }
}
