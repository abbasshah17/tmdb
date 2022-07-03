package com.starzplay.ext

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.starzplay.base.views.recycler.RecyclerAdapter

fun <ViewBinding: ViewDataBinding, T: Any> RecyclerAdapter<ViewBinding, T>.merge(newList: MutableList<out T>) {

    when {
        newList.isEmpty() -> {
            notifyItemRangeRemoved(0, itemCount)

            list.clear()
        }

        list.isEmpty() -> {
            list.addAll(newList)

            notifyItemRangeInserted(0, itemCount)
        }

        else -> {

            val callback = object : RecyclerDiffCallback(list, newList) {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return areItemsSame(list[oldItemPosition], newList[newItemPosition])
                }
            }

            val diffResult = DiffUtil.calculateDiff(callback)

            list.clear()
            list.addAll(newList)

            diffResult.dispatchUpdatesTo(this)

        }
    }
}

abstract class RecyclerDiffCallback(
    private val list: List<Any>?,
    private val newList: List<Any>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return list?.size ?: 0
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return getItemAt(oldItemPosition, list) == getItemAt(newItemPosition, newList)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return getItemAt(newItemPosition, newList)
    }

    fun getItemAt(position: Int, list: List<Any>?): Any? {
        return list?.get(position)
    }
}

fun RecyclerView.isLinearLayoutManagerAttached(): Boolean {
    return layoutManager is LinearLayoutManager
}

fun RecyclerView.isGridLayoutManagerAttached(): Boolean {
    return layoutManager is GridLayoutManager
}