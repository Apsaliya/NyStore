package com.ankit.nystore.di

import com.ankit.nystore.feature.FeedViewModel
import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
  
  @Subcomponent.Builder
  interface Builder {
    fun build(): ViewModelSubComponent
  }
  
  fun provideFeedViewModel(): FeedViewModel
}