package com.starzplay.view.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.starzplay.R
import com.starzplay.base.views.BaseFragment
import com.starzplay.databinding.ContentItemDetailLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentItemDetailFragment: BaseFragment<ContentItemDetailLayoutBinding>() {

    override val layout: Int
        get() = R.layout.content_item_detail_layout


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindData()

        setupInteractions()
    }

    val args: ContentItemDetailFragmentArgs by navArgs()

    private fun bindData() {
        binding.content = args.contentItem
    }

    private fun setupInteractions() {
//        TODO("Not yet implemented")
    }
}