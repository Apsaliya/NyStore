package com.ankit.nystore.feature

import android.arch.lifecycle.MutableLiveData
import com.ankit.nystore.base.BaseViewModel
import com.ankit.nystore.exceptions.NoDataFoundException
import com.ankit.nystore.feature.data.LocalDataSource
import com.ankit.nystore.feature.data.Repo
import com.ankit.nystore.store.StoriesDatabase
import com.ankit.nystore.store.entities.Story
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class FeedViewModel @Inject constructor(val repo: Repo) : BaseViewModel() {
  data class ViewState(val stories: List<Story>? = null,
                       val showError: Boolean? = false,
                       val errorMessage: String? = null)

  val viewState: MutableLiveData<ViewState> = MutableLiveData()

  init {
    viewState.value = ViewState()
    getTopStories()
  }

  fun getTopStories() {
    repo.getAllStories("aaeb19167ede4e33995f6e9b74764c7e")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe( {
          Timber.d("size " + it.size)
          viewState.value = ViewState(stories = it)
        }, {
          if (it is NoDataFoundException) {
            viewState.value = ViewState(showError = true, errorMessage = "Could not fetch data!")
          } else {
            //no other exception is valid from this stream. throw right here instead of swallowing.
            throw it
          }
        })
  }
}