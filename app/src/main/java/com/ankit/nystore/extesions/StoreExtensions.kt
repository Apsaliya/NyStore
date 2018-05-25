@file:Suppress("NOTHING_TO_INLINE")

package com.ankit.nystore.extesions

import com.ankit.nystore.store.entities.Story

inline fun Story.getStoryImageUrl() : String? {
  var url: String? = null
  if (multimedia!!.size >= 4) {
    url = multimedia!![4].url
  } else if (multimedia!!.size > 0) {
    url =multimedia!![0].url
  }
  return url
}