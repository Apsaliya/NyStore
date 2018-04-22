@file:Suppress("UNCHECKED_CAST")

package com.ankit.nystore.feature

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.util.ArrayMap
import com.ankit.nystore.feature.data.Repo
import javax.inject.Inject

internal class FeedViewModelFactory(val repo: Repo) : ViewModelProvider.Factory {
  private val creators = ArrayMap<Class<*>, ViewModel>()
  
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
      return modelClass.getConstructor(Repo::class.java).newInstance(repo)
    }
    
    throw IllegalStateException("Unknown ViewModel class.")
  }
}