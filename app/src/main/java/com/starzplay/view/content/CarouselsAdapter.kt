package com.starzplay.view.content

import android.view.LayoutInflater
import android.view.ViewGroup
import com.starzplay.R
import com.starzplay.base.views.recycler.RecyclerAdapter
import com.starzplay.base.views.recycler.RecyclerViewHolder
import com.starzplay.databinding.CarouselLayoutBinding
import com.tmdb.domain.content.data.response.CarouselList

class CarouselsAdapter: RecyclerAdapter<CarouselLayoutBinding, CarouselList>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder<CarouselLayoutBinding, CarouselList> {
        return CarouselViewHolder(
            binding = inflateBinding(LayoutInflater.from(parent.context), viewType)
        )
    }

    override fun areItemsSame(oldItem: CarouselList?, newItem: CarouselList?): Boolean {
        return (oldItem?.carousel?.name?.equals(newItem?.carousel?.name) == true)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.carousel_layout
    }
}