package com.ankit.nystore.feature.data

import com.ankit.nystore.exceptions.NoDataFoundException
import com.ankit.nystore.store.entities.Story
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class Repo @Inject constructor(val localDataSource: LocalDataSource, val remoteDataSource: RemoteDataSource) : DataSource {
  
  override fun getAllStories(key: String): Flowable<List<Story>> {
    return Flowable.zip(localDataSource
        .getAllStories(key).onErrorReturn { ArrayList() } , remoteDataSource.getAllStories(key).onErrorReturn { ArrayList() }
        , BiFunction<List<Story>, List<Story>, List<Story>> { t1, t2 ->
      Flowable.just(t1, t2)
          .flatMapIterable { t -> t }
          .distinct { t ->
            return@distinct t.created_date
          }
          .toList()
          .toFlowable()
          .blockingFirst()
    })
        .doOnNext { addStories(it) }
        .flatMap { t ->
          if (t.isEmpty()) {
            Flowable.error(NoDataFoundException("No data found."))
          } else {
            Flowable.just(t)
          }
        }
        .switchIfEmpty(Flowable.error(NoDataFoundException("No data found.")))
  }
  
  override fun addStories(stories: List<Story>) {
    localDataSource.addStories(stories)
    remoteDataSource.addStories(stories)
  }
}