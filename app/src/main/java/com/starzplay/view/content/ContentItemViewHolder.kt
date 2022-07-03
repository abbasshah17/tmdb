package com.starzplay.view.content

import androidx.navigation.findNavController
import com.starzplay.base.views.recycler.RecyclerViewHolder
import com.starzplay.databinding.ContentItemLayoutBinding
import com.tmdb.domain.content.data.response.ContentItem

class ContentItemViewHolder(
    binding: ContentItemLayoutBinding
): RecyclerViewHolder<ContentItemLayoutBinding, ContentItem>(
    binding
) {
    override fun bind(data: ContentItem) {
        binding.content = data

        binding.root.setOnClickListener {
            binding.content?.let {
                val action = ContentFragmentDirections.contentDetail(it)
                binding.root.findNavController().navigate(action)
            }
        }
    }
}