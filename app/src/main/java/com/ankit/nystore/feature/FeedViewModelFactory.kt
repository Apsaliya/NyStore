package com.ankit.nystore.feature

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ankit.nystore.store.daos.StoriesDao

internal class FeedViewModelFactory(private val storiesDao : StoriesDao) : ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
      return modelClass.getConstructor(StoriesDao::class.java).newInstance(storiesDao)
    }
    throw IllegalStateException("Unknown ViewModel class.")
  }
}