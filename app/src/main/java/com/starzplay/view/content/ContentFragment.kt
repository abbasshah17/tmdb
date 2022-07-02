package com.starzplay.view.content

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import com.starzplay.R
import com.starzplay.base.views.BaseFragment
import com.starzplay.databinding.SearchContentLayoutBinding

class ContentFragment: BaseFragment<SearchContentLayoutBinding>() {

    override val layout: Int
        get() = R.layout.search_content_layout


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAnimations()
    }

    private fun setupAnimations() = binding.apply {
        searchQueryField.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                searchQueryField.clearFocus()
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
}