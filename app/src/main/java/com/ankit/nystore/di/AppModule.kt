package com.ankit.nystore.di

import com.ankit.nystore.App
import com.ankit.nystore.networking.ApiService
import com.ankit.nystore.store.StoriesDatabase
import com.ankit.nystore.store.daos.StoriesDao
import com.ankit.nystore.util.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
  
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    
    okHttpBuilder = okHttpBuilder.addInterceptor({ chain ->
      val original = chain.request()
      
      val request = original.newBuilder()
          .header("Accept", "application/json")
          .build()
      
      chain.proceed(request)
    })
    okHttpBuilder.interceptors().add(loggingInterceptor)
    val okHttpClient = okHttpBuilder.build()
    
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
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