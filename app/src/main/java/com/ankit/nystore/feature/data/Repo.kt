package com.ankit.nystore.feature.data

import com.ankit.nystore.store.entities.Story
import io.reactivex.Flowable
import javax.inject.Inject

class Repo @Inject constructor(val localDataSource: LocalDataSource, val remoteDataSource: RemoteDataSource) : DataSource {
  
  override fun getAllStories(key : String): Flowable<List<Story>> {
    return Flowable.concat(localDataSource.getAllStories(key), remoteDataSource.getAllStories(key))
  }
}