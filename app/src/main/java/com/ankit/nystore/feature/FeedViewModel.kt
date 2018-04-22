package com.ankit.nystore.feature

import android.arch.lifecycle.MutableLiveData
import com.ankit.nystore.store.daos.StoriesDao
import com.ankit.nystore.base.BaseViewModel
import com.ankit.nystore.store.entities.Story

class FeedViewModel(private val storiesDao: StoriesDao) : BaseViewModel() {
  data class ViewState(val reports: List<Story>? = null,
                       val showError: Boolean? = false,
                       val errorMessage: String? = null)

  val viewState: MutableLiveData<ViewState> = MutableLiveData()

  init {
    viewState.value = ViewState()
  }

  fun getCurrentViewState() = viewState.value!!
  
}