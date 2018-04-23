package com.ankit.nystore.store.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.ankit.nystore.store.StoriesDatabase
import com.ankit.nystore.store.entities.Story
import io.reactivex.Flowable

@Dao interface StoriesDao {
  @Query("SELECT * FROM ${Story.TABLE_NAME}")
  fun getAllStories(): Flowable<List<Story>>
  
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun saveReport(stories: List<Story>)
}