package com.ankit.nystore

import android.app.Application
import android.content.Context
import android.support.multidex.BuildConfig
import android.support.multidex.MultiDex
import com.ankit.nystore.di.AppComponent
import com.ankit.nystore.di.AppModule
import com.ankit.nystore.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {
  var myComponent: AppComponent? = null
  
  override fun onCreate() {
    super.onCreate()
    
    Timber.plant(Timber.DebugTree())
    
    myComponent = DaggerAppComponent
        .builder()
        .appModule(AppModule(this))
        .build()
    myComponent?.inject(this)
  }
  
  override fun attachBaseContext(base: Context?) {
    super.attachBaseContext(base)
    MultiDex.install(this)
  }
  
}