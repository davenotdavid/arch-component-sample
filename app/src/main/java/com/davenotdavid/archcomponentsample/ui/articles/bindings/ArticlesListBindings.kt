package com.davenotdavid.archcomponentsample.ui.articles.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.davenotdavid.archcomponentsample.R
import com.davenotdavid.archcomponentsample.model.Article
import com.davenotdavid.archcomponentsample.ui.articles.adapter.ArticlesAdapter

/**
 * [BindingAdapter] for the [Article] list.
 */
@BindingAdapter("app:items")
fun setItems(recyclerView: RecyclerView, items: List<Article>) {
    (recyclerView.adapter as ArticlesAdapter).submitList(items)
}

/**
 * Handles image data binding for image loading/caching via [Glide].
 */
@BindingAdapter("imageUrl")
fun loadImage(image: ImageView, url: String?) =
    Glide
        .with(image.context)
        .load(url)
        .placeholder(R.drawable.ic_placeholder)
        .into(image)

