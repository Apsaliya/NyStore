@file:Suppress("UNCHECKED_CAST")

package com.ankit.nystore.feature

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ankit.nystore.feature.data.Repo

internal class FeedViewModelFactory(val repo: Repo) : ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
      return modelClass.getConstructor(Repo::class.java).newInstance(repo)
    }
    
    throw IllegalStateException("Unknown ViewModel class.")
  }
}