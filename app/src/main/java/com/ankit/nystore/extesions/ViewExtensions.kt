@file:Suppress("NOTHING_TO_INLINE")

package com.ankit.nystore.extesions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

inline fun View.show() {
  visibility = VISIBLE
}

inline fun View.hide() {
  visibility = GONE
}