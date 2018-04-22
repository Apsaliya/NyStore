package com.ankit.nystore.store.converters

import android.arch.persistence.room.TypeConverter
import com.ankit.nystore.store.entities.MultiMedia
import com.ankit.nystore.util.NGson

class Converters {
  
  @TypeConverter
  fun fromMultiMedia(value: ArrayList<MultiMedia>?) = NGson.gson.toJson(value)
  
  @TypeConverter
  fun toMultiMedia(multiMedia: String?): ArrayList<MultiMedia>? =
      NGson.gson.fromJson<ArrayList<MultiMedia>>(multiMedia, ArrayList::class.java)
}