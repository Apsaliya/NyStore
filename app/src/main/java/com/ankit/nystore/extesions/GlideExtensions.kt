package com.ankit.nystore.extesions

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


inline fun RequestBuilder<Drawable>.onRequestCompleted(crossinline success: () -> Any, crossinline fail: () -> Any) : RequestBuilder<Drawable> {
  return listener(object : RequestListener<Drawable> {
    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
      success()
      return false
    }
    
    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
      fail()
      return false
    }
  })}