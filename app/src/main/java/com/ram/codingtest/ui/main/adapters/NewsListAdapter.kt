package com.ram.codingtest.ui.main.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ram.codingtest.R
import com.ram.codingtest.listeners.NewsItemClickListener
import com.ram.codingtest.loadImage
import com.ram.codingtest.model.News

class NewsListAdapter(listener: NewsItemClickListener) : RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    private var newList: MutableList<News> = ArrayList()
    private val listener = listener

    fun setNews(news: MutableList<News>) {
        newList.addAll(news)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_list_item, parent, false)

        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newList[position]
        news.let {
            it.image?.let { it1 -> holder.newsImg.loadImage(it1) }
            it.title?.let { txt -> holder.newsTitle.text=txt}
        }
        holder.root.setOnClickListener {
            newList[position].url?.let { it1 -> listener.onItemClick(it1) }
        }
    }

    class NewsViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        val root = parent.findViewById<LinearLayout>(R.id.root)
        val newsImg = parent.findViewById<ImageView>(R.id.news_img)
        val newsTitle = parent.findViewById<TextView>(R.id.news_title)
    }
}