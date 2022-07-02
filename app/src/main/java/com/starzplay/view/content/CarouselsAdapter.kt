package com.starzplay.view.content

import android.view.LayoutInflater
import android.view.ViewGroup
import com.starzplay.base.views.recycler.RecyclerAdapter
import com.starzplay.base.views.recycler.RecyclerViewHolder
import com.starzplay.databinding.CarouselLayoutBinding
import com.tmdb.domain.content.data.response.Carousel

class CarouselsAdapter: RecyclerAdapter<CarouselLayoutBinding, Carousel>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder<CarouselLayoutBinding, Carousel> {
        return CarouselViewHolder(inflateBinding(LayoutInflater.from(parent.context), viewType))
    }
}