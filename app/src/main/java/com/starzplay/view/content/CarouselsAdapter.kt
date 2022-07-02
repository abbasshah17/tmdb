package com.starzplay.view.content

import android.view.LayoutInflater
import android.view.ViewGroup
import com.starzplay.base.views.recycler.RecyclerAdapter
import com.starzplay.base.views.recycler.RecyclerViewHolder
import com.starzplay.databinding.CarouselLayoutBinding
import com.tmdb.domain.content.data.response.CarouselList

class CarouselsAdapter: RecyclerAdapter<CarouselLayoutBinding, CarouselList>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder<CarouselLayoutBinding, CarouselList> {
        return CarouselViewHolder(inflateBinding(LayoutInflater.from(parent.context), viewType))
    }
}