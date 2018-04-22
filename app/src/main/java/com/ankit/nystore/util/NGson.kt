package com.ankit.nystore.util

import com.google.gson.Gson

object NGson {
  val gson: Gson by lazy {
    Gson()
  }
}