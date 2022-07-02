package com.starzplay.base.views.recycler

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerAdapter<ItemViewBinding: ViewDataBinding, Model>
    : RecyclerView.Adapter<RecyclerViewHolder<ItemViewBinding, Model>>() {

    var list: MutableList<Model> = mutableListOf()
    set(value) {
        field = value
    }


    override fun getItemCount(): Int = list.size

    fun inflateBinding(
        inflater: LayoutInflater,
        layout: Int
    ): ItemViewBinding {
        return DataBindingUtil.inflate(inflater, layout, null, false)
    }

    override fun onBindViewHolder(
        holder: RecyclerViewHolder<ItemViewBinding, Model>,
        position: Int
    ) {
        holder.bind(list[position])
    }

    abstract fun areItemsSame(oldItem: Model?, newItem: Model?): Boolean
}