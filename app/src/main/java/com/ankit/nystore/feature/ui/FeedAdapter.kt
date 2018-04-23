package com.ankit.nystore.feature.ui

import android.content.Context
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ankit.nystore.extesions.hide
import com.ankit.nystore.extesions.onRequestCompleted
import com.ankit.nystore.extesions.show
import com.ankit.nystore.store.entities.Story
import com.bumptech.glide.Glide
import com.example.ankit.nystore.R
import kotlinx.android.synthetic.main.item_story.view.*
import java.util.*

internal class FeedAdapter(var context: Context) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
  
  val stories = ArrayList<Story>()
  
  override fun getItemCount() = stories.size
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false)
    return ViewHolder(itemView)
  }
  
  fun dispatchUpdates(newPosts: List<Story>) {
    stories.clear()
    stories.addAll(newPosts)
    notifyDataSetChanged()
  }
  
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val story = stories[position]
    holder.itemView.primary_text.text = story.title
    holder.itemView.sub_text.text = story.subTitle
    holder.itemView.tag = story
    holder.itemView.spinKitStory.show()
    val url = if (story.multimedia!!.size >= 4) {
      story.multimedia!![4].url
    } else {
      story.multimedia!![0].url
    }
    Glide.with(context)
        .load(url)
        .onRequestCompleted({
          holder.itemView.spinKitStory.hide()
        }, {
          holder.itemView.spinKitStory.hide()
        })
        .into(holder.itemView.media_image)
  }
  
  override fun onViewRecycled(holder: ViewHolder?) {
    super.onViewRecycled(holder)
    holder?.itemView?.spinKitStory?.hide()
  }
  
  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    init {
      itemView.setOnClickListener {
        val story = it.tag as Story
        CustomTabsIntent.Builder()
            .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
            .addDefaultShareMenuItem()
            .build()
            .launchUrl(context, Uri.parse(story.url))
      }
    }
  }
}