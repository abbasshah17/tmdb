package com.starzplay.base.views.recycler

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewHolder<RecyclerItemViewBinding: ViewDataBinding, Model>(
    protected val binding: RecyclerItemViewBinding
): RecyclerView.ViewHolder(
    binding.root
) {
    abstract fun bind(data: Model)
}