package com.ankit.nystore.di

import com.ankit.nystore.App
import com.ankit.nystore.feature.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
  fun inject(app: App)
  fun inject(mainActivity: MainActivity)
}