package com.starzplay.view.content

import com.starzplay.base.views.recycler.RecyclerViewHolder
import com.starzplay.databinding.CarouselLayoutBinding
import com.tmdb.domain.content.data.response.Carousel

class CarouselViewHolder(
    binding: CarouselLayoutBinding
): RecyclerViewHolder<CarouselLayoutBinding, Carousel>(
    binding
) {
    override fun bind(data: Carousel) {
        binding.carousel = data
    }
}