package com.starzplay.view.content

import com.starzplay.base.views.recycler.RecyclerViewHolder
import com.starzplay.databinding.CarouselLayoutBinding
import com.tmdb.domain.content.data.response.CarouselList

class CarouselViewHolder(
    binding: CarouselLayoutBinding
): RecyclerViewHolder<CarouselLayoutBinding, CarouselList>(
    binding
) {
    override fun bind(data: CarouselList) {
        binding.carousel = data.carousel
    }
}