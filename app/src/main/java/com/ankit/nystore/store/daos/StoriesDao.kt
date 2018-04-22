package com.ankit.nystore.store.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.ankit.nystore.store.entities.Story
import io.reactivex.Flowable

@Dao interface StoriesDao {
  @Query("SELECT * FROM Details")
  fun getAllStories(): Flowable<List<Story>>
}