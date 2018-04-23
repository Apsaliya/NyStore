package com.ankit.nystore.feature.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ankit.nystore.store.entities.Story
import com.example.ankit.nystore.R
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.item_story.view.*
import java.util.concurrent.TimeUnit

internal class FeedAdapter(var context: Context) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
  
  lateinit var itemClickListener: OnItemClickListener
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
    holder.itemView.sub_text.text = story.abstract
    holder.itemView.tag = story
  }
  
  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    init {
    
    }
  }
  
  interface OnItemClickListener {
    fun onItemClick(clickListener: ClickListener)
  }
  
  sealed class ClickListener {
    //data class LikeClick(val post: Post, val position: Int) : ClickListener()
    //data class ImageClick(val post: Post, val position: Int) : ClickListener()
    //data class CommentClick(val post: Post) : ClickListener()
  }
}