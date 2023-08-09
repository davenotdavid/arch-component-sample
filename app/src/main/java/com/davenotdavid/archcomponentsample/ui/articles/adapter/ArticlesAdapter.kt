package com.davenotdavid.archcomponentsample.ui.articles.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.davenotdavid.archcomponentsample.R
import com.davenotdavid.archcomponentsample.databinding.ListItemArticleBinding
import com.davenotdavid.archcomponentsample.model.Article
import com.davenotdavid.archcomponentsample.ui.articles.ArticlesViewModel

/**
 * Adapter for the article list.
 *
 * TODO: Remove when ready
 */
//class ArticlesAdapter(private val listener: OnArticleClickListener) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater
//            .from(parent.context)
//            .inflate(R.layout.list_item_article, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val article = getItem(position)
//        holder.bind(article)
//    }
//
//    class ViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {
//
//        fun bind(article: Article?) {
//            // TODO
//        }
//    }
//
//    interface OnArticleClickListener {
//        fun onArticleItemClick()
//    }
//
//}
