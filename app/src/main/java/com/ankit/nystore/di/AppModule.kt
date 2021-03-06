package com.ankit.nystore.di

import com.ankit.nystore.App
import com.ankit.nystore.networking.ApiService
import com.ankit.nystore.store.StoriesDatabase
import com.ankit.nystore.store.daos.StoriesDao
import com.example.ankit.nystore.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(val app: App) {
  
  @Provides
  @Singleton
  internal fun application(app: App) = app
  
  @Provides
  @Singleton
  fun provideApiService(): ApiService {
    var okHttpBuilder = OkHttpClient.Builder()
  
    okHttpBuilder = okHttpBuilder.addInterceptor({ chain ->
      val original = chain.request()
      
      val request = original.newBuilder()
          .header("Accept", "application/json")
          .build()
      
      chain.proceed(request)
    })
    val okHttpClient = okHttpBuilder.build()
    
    return Retrofit.Builder()
        .baseUrl(BuildConfig.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(ApiService::class.java)
  }
  
  @Provides
  @Singleton
  fun provideStoriesDao(): StoriesDao = StoriesDatabase.getInstance(app).storiesDao()
}