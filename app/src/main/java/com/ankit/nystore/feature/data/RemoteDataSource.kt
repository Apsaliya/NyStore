package com.ankit.nystore.feature.data

import com.ankit.nystore.networking.ApiService
import com.ankit.nystore.store.entities.Story
import io.reactivex.Flowable
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val service: ApiService) : DataSource {
  
  override fun getAllStories(key : String): Flowable<List<Story>> {
    return service.getTopStories(apiKey = key)
        .map { t -> t.results }
  }
}