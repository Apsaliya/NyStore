package com.ankit.nystore

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.ankit.nystore.di.AppComponent
import com.ankit.nystore.di.DaggerAppComponent

class App : Application() {
  private var myComponent: AppComponent? = null
  
  override fun onCreate() {
    super.onCreate()
    myComponent = DaggerAppComponent.builder().application(this)
        .build()
    myComponent?.inject(this)
  }
  
  override fun attachBaseContext(base: Context?) {
    super.attachBaseContext(base)
    MultiDex.install(this)
  }
  
}