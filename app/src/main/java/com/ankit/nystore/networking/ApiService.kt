package com.ankit.nystore.networking

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
  //TODO Create custom Retrofit Converter to directly get Flowable<List<Story>>
  @GET("topstories/v2/home.json")
  fun getTopStories(@Query("api-key") apiKey: String): Flowable<TopStories>
}