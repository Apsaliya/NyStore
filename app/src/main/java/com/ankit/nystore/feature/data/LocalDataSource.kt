package com.ankit.nystore.feature.data

import com.ankit.nystore.store.daos.StoriesDao
import com.ankit.nystore.store.entities.Story
import io.reactivex.Flowable
import javax.inject.Inject

class LocalDataSource @Inject constructor(val storiesDao: StoriesDao) : DataSource {
  
  override fun getAllStories(key : String): Flowable<List<Story>> {
    return storiesDao.getAllStories()
  }
  
  override fun addStories(stories: List<Story>) = storiesDao.saveReport(stories)
}