package com.ankit.nystore.di

import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
  
  @Subcomponent.Builder
  interface Builder {
    fun build(): AppComponent
  }
}