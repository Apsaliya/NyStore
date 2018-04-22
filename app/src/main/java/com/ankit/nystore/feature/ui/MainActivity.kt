package com.ankit.nystore.feature.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ankit.nystore.base.BaseActivity
import com.example.ankit.nystore.R

class MainActivity : BaseActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }
  
  override fun initDagger() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
