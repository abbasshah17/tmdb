package com.starzplay.view.recycler.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.starzplay.ext.isGridLayoutManagerAttached
import com.starzplay.ext.isLinearLayoutManagerAttached

class TopAnchorDecorator(
    private val searchInfoTitle: View
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        if (parent.isGridLayoutManagerAttached()) {
            val layoutManager = parent.layoutManager as GridLayoutManager
            val spanCount = layoutManager.spanCount

            if (position < spanCount) {
                outRect.top = calculateAnchorSpace(searchInfoTitle)
            }
        } else if (parent.isLinearLayoutManagerAttached()) {
            if (position == 0) {
                outRect.top = calculateAnchorSpace(searchInfoTitle)
            }
        }
    }

    private fun calculateAnchorSpace(searchInfoTitle: View): Int {
        return searchInfoTitle.top + searchInfoTitle.height
    }
}
