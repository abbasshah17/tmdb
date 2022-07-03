package com.starzplay.view.content

import androidx.recyclerview.widget.LinearLayoutManager
import com.starzplay.base.views.recycler.RecyclerViewHolder
import com.starzplay.databinding.CarouselLayoutBinding
import com.starzplay.ext.merge
import com.tmdb.domain.content.data.response.CarouselList
import com.tmdb.domain.content.data.response.ContentItem

class CarouselViewHolder(
    binding: CarouselLayoutBinding
): RecyclerViewHolder<CarouselLayoutBinding, CarouselList>(
    binding
) {
    private val carouselAdapter = CarouselAdapter()
    init {
        binding.carouselItems.adapter = carouselAdapter
        binding.carouselItems.layoutManager = LinearLayoutManager(
            binding.root.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }
    override fun bind(data: CarouselList) {
        binding.carousel = data.carousel
        carouselAdapter.merge(data.list as MutableList<ContentItem>)
    }
}