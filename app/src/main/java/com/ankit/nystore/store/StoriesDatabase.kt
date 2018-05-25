package com.ankit.nystore.store

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.ankit.nystore.store.StoriesDatabase.Companion.DB_VERSION
import com.ankit.nystore.store.converters.Converters
import com.ankit.nystore.store.daos.StoriesDao
import com.ankit.nystore.store.entities.Story

@Database(entities = [(Story::class)], version = DB_VERSION)
@TypeConverters(Converters::class)
abstract class StoriesDatabase : RoomDatabase() {
  
  abstract fun storiesDao(): StoriesDao
  
  companion object {
    const val DB_VERSION = 1
    const val DB_NAME = "Stories.db"
    private var INSTANCE: StoriesDatabase? = null
    
    fun getInstance(context: Context): StoriesDatabase {
      if (INSTANCE == null) {
        SQLiteDatabase.create(null)
        synchronized(StoriesDatabase::class) {
          INSTANCE = Room.databaseBuilder(context.applicationContext,
              StoriesDatabase::class.java, DB_NAME)
              .build()
        }
      }
      return INSTANCE!!
    }
  }
}