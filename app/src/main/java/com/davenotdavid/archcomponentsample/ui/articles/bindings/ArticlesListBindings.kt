package com.davenotdavid.archcomponentsample.ui.articles.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.davenotdavid.archcomponentsample.model.Article
import com.davenotdavid.archcomponentsample.ui.articles.adapter.ArticlesAdapter

/**
 * [BindingAdapter] for the [Article] list.
 */
@BindingAdapter("app:items")
fun setItems(recyclerView: RecyclerView, items: List<Article>) {
    (recyclerView.adapter as ArticlesAdapter).submitList(items)
}
