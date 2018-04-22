package com.ankit.nystore.feature.data

import com.ankit.nystore.store.entities.Story
import io.reactivex.Flowable

interface DataSource {
  fun getAllStories(key: String) : Flowable<List<Story>>
}