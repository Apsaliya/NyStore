package com.ankit.nystore.feature

import android.arch.lifecycle.MutableLiveData
import com.ankit.nystore.base.BaseViewModel
import com.ankit.nystore.exceptions.NoDataFoundException
import com.ankit.nystore.feature.data.Repo
import com.ankit.nystore.store.entities.Story
import com.example.ankit.nystore.BuildConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FeedViewModel constructor(val repo: Repo): BaseViewModel() {
  data class ViewState(val stories: List<Story>? = null,
                       val showError: Boolean? = false,
                       val showLoader: Boolean? = false,
                       val noData: Boolean? = false)

  val viewState: MutableLiveData<ViewState> = MutableLiveData()

  init {
    viewState.value = ViewState()
    getTopStories()
  }

  fun getTopStories() {
    addDisposible(repo.getAllStories(BuildConfig.apiKey)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe {
          viewState.value = ViewState(showLoader = true)
        }
        .subscribe( {
          viewState.value = ViewState(stories = it)
        }, {
          if (it is NoDataFoundException) {
            viewState.value = ViewState(showError = true, noData = true)
          } else {
            //no other exception is valid from this stream. throw right here instead of swallowing.
            throw it
          }
        }))
  }
}