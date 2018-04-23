package com.ankit.nystore.feature.data

import com.ankit.nystore.store.entities.Story
import io.reactivex.Flowable
import io.reactivex.functions.Consumer
import javax.inject.Inject

class Repo @Inject constructor(val localDataSource: LocalDataSource, val remoteDataSource: RemoteDataSource) : DataSource {
  
  override fun getAllStories(key : String): Flowable<List<Story>> {
    return Flowable.mergeDelayError(localDataSource.getAllStories(key),
        remoteDataSource.getAllStories(key))
        .flatMapIterable { t -> t }
        .distinct { t -> return@distinct t.created_date }
        .toList()
        .toFlowable()
        .doOnNext { addStories(it) }
  }
  
  override fun addStories(stories: List<Story>) {
    localDataSource.addStories(stories)
    remoteDataSource.addStories(stories)
  }
}