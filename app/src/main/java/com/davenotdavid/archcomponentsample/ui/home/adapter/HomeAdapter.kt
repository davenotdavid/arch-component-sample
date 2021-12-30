package com.davenotdavid.archcomponentsample.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.davenotdavid.archcomponentsample.databinding.ListItemArticleBinding
import com.davenotdavid.archcomponentsample.model.Article
import com.davenotdavid.archcomponentsample.ui.home.HomeViewModel

/**
 * Adapter for the article list. Has a reference to the [HomeViewModel] to send
 * actions back to it.
 *
 * TODO: Consider [RecyclerView] later on
 */
class HomeAdapter(private val homeViewModel: HomeViewModel) :
    ListAdapter<Article, HomeAdapter.ViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(private val binding: ListItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article?) {
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
        // TODO: Generally with IDs instead, but temporarily using `title` for now
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}
