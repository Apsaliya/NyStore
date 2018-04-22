package com.ankit.nystore.di

import com.ankit.nystore.networking.ApiService
import com.ankit.nystore.util.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(subcomponents = [(ViewModelSubComponent::class)])
class AppModule {
  
  @Provides
  @Singleton
  fun provideApiService(): ApiService {
    val okHttpBuilder = OkHttpClient.Builder()
  
    okHttpBuilder.addInterceptor({ chain ->
      val original = chain.request()
    
      val request = original.newBuilder()
          .header("Accept", "application/json")
          .build()
    
      chain.proceed(request)
    })
    val okHttpClient = okHttpBuilder.build()
  
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(ApiService::class.java)
  }
}