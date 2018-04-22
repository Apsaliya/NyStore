package com.ankit.nystore.di

import android.app.Application
import com.ankit.nystore.App
import com.ankit.nystore.feature.ui.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [(AppModule::class)])
interface AppComponent {
  
  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(app: Application): Builder
    
    fun build(): AppComponent
  }
  fun inject(app: App)
  fun inject(mainActivity: MainActivity)
}