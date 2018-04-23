package com.ankit.nystore.store.converters

import android.arch.persistence.room.TypeConverter
import com.ankit.nystore.store.entities.MultiMedia
import com.ankit.nystore.util.NGson
import com.google.gson.reflect.TypeToken
import java.util.*


class Converters {
  
  @TypeConverter
  fun fromMultiMedia(value: ArrayList<MultiMedia>?) = NGson.gson.toJson(value)
  
  @TypeConverter
  fun toMultiMedia(multiMedia: String?): ArrayList<MultiMedia>? {
    val turnsType = object : TypeToken<ArrayList<MultiMedia>>() {}.type
    return NGson.gson.fromJson<ArrayList<MultiMedia>>(multiMedia, turnsType)
  }
}