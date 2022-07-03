package com.starzplay.view.content

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.starzplay.R
import com.starzplay.base.views.BaseFragment
import com.starzplay.databinding.SearchContentLayoutBinding
import com.starzplay.ext.merge
import com.tmdb.domain.content.ContentViewModel

class ContentFragment: BaseFragment<SearchContentLayoutBinding>() {

    override val layout: Int
        get() = R.layout.search_content_layout


    private val viewModel: ContentViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAnimations()

        setupList()

        setupObservers()
    }

    private fun setupAnimations() = binding.apply {
        searchQueryField.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (
                actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_NEXT ||
                actionId == EditorInfo.IME_ACTION_SEND ||
                actionId == EditorInfo.IME_ACTION_SEARCH
            ) {
                performSearch(textView.text.toString())
                true
            } else {
                false
            }
        }
                true
            } else {
                false
            }
        }

        searchQueryField.setOnFocusChangeListener { view, focus ->
            if (focus) {
                content.transitionToState(R.id.start)
            } else {
                content.transitionToState(R.id.end)
            }
        }
    }

    private fun performSearch(text: String) {
        viewModel.searchContent(
            query = text
        )
        binding.searchResultsShimmer.beVisible()
        binding.searchQueryField.clearFocus()
        binding.searchQueryField.hideKeyboard()
        binding.carousels.scrollBy(0, -1000)
    }

    private var carouselsAdapter: CarouselsAdapter? = null
    private fun setupList() = binding.carousels.apply {
        carouselsAdapter = CarouselsAdapter()
        adapter = carouselsAdapter
        layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun setupObservers() {
        viewModel.carousels.observe(viewLifecycleOwner) { carousels ->
            carousels?.let { it ->
                carouselsAdapter?.merge(it)
            }
        }
    }
}
