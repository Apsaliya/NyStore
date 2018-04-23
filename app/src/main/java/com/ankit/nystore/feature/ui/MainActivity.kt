package com.ankit.nystore.feature.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout.VERTICAL
import com.ankit.nystore.App
import com.ankit.nystore.base.BaseActivity
import com.ankit.nystore.extesions.hasInternetConnection
import com.ankit.nystore.extesions.hide
import com.ankit.nystore.extesions.show
import com.ankit.nystore.extesions.showSnackBar
import com.ankit.nystore.feature.FeedViewModel
import com.ankit.nystore.feature.FeedViewModelFactory
import com.ankit.nystore.feature.data.Repo
import com.example.ankit.nystore.R
import kotlinx.android.synthetic.main.activity_main.*
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
    recycler.layoutManager = LinearLayoutManager(this@MainActivity, VERTICAL, false)
    val newAdapter = FeedAdapter(this)
    recycler.adapter = newAdapter
    observeData()
  }
  
  private fun observeData() {
    feedViewModel.viewState.observe(this, Observer {
      if (it?.stories != null) {
        val adapter = recycler.adapter
        (adapter as FeedAdapter).dispatchUpdates(it.stories)
      }
    
      if (it?.showError != null && it.showError) {
        spinKitFeed.hide()
        if (it.noData != null && it.noData) {
          showSnackBar(getString(R.string.error_no_data))
        } else if (!hasInternetConnection()) {
          showSnackBar(getString(R.string.error_no_data_no_internet))
        } else {
          showSnackBar(getString(R.string.error_generic))
        }
      }
    
      if (it?.showLoader != null && it.showLoader) {
        spinKitFeed.show()
      } else {
        spinKitFeed.hide()
      }
    })
  }
  
  override fun initDagger() {
    (application as App).myComponent?.inject(this)
  }
}
