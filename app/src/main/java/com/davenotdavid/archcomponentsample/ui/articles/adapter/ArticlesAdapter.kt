package com.davenotdavid.archcomponentsample.ui.articles.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.davenotdavid.archcomponentsample.databinding.ListItemArticleBinding
import com.davenotdavid.archcomponentsample.model.Article
import com.davenotdavid.archcomponentsample.ui.articles.ArticlesViewModel

/**
 * Adapter for the article list. Has a reference to the [ArticlesViewModel] to send
 * actions back to it.
 */
class ArticlesAdapter(private val articlesViewModel: ArticlesViewModel) :
    ListAdapter<Article, ArticlesAdapter.ViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(articlesViewModel, item)
    }

    class ViewHolder private constructor(private val binding: ListItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(articlesViewModel: ArticlesViewModel, article: Article?) {
            binding.viewmodel = articlesViewModel
            binding.article = article
            // Updates the view bound to modified vars.
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemArticleBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between an old list and a new
 * list that's been passed to `submitList`.
 */
class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}
