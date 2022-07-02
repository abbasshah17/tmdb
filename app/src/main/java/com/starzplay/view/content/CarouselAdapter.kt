package com.starzplay.view.content

import android.view.LayoutInflater
import android.view.ViewGroup
import com.starzplay.base.views.recycler.RecyclerAdapter
import com.starzplay.base.views.recycler.RecyclerViewHolder
import com.starzplay.databinding.ContentItemLayoutBinding
import com.tmdb.domain.content.data.response.ContentItem

class CarouselAdapter: RecyclerAdapter<ContentItemLayoutBinding, ContentItem>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder<ContentItemLayoutBinding, ContentItem> {
        return ContentItemViewHolder(inflateBinding(LayoutInflater.from(parent.context), viewType))
    }
}