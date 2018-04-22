package com.ankit.nystore.feature.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.ankit.nystore.App
import com.ankit.nystore.base.BaseActivity
import com.ankit.nystore.feature.FeedViewModel
import com.ankit.nystore.feature.FeedViewModelFactory
import com.ankit.nystore.feature.data.Repo
import com.example.ankit.nystore.R
import javax.inject.Inject

class MainActivity : BaseActivity() {
  
  internal lateinit var feedViewModel: FeedViewModel
  
  @Inject
  internal lateinit var repo: Repo
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    feedViewModel = ViewModelProviders.of(this@MainActivity, FeedViewModelFactory(repo))
        .get(FeedViewModel::class.java)
  }
  
  override fun initDagger() {
    (application as App).myComponent?.inject(this)
  }
}
