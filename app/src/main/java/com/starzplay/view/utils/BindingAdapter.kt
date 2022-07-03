package com.starzplay.view.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.tmdb.domain.images.parseTmdbResourcePath

object BindingAdapter {

    @BindingAdapter(value = ["app:imageUrl", "app:placeholder"], requireAll = false)
    @JvmStatic
    fun bindImageWithUrl(imageView: ImageView?, url: String? = null, placeholder: Drawable? = null) {

        var requestBuilder = Glide.with(imageView!!)
            .load(url)

        if (placeholder != null) {
            requestBuilder = requestBuilder
                .placeholder(placeholder)
        }

        requestBuilder
            .into(imageView)

    }

    @BindingAdapter(value = ["tmdb:imagePath", "app:placeholder"], requireAll = false)
    @JvmStatic
    fun bindTmdbPathtoImage(imageView: ImageView?, url: String? = null, placeholder: Drawable? = null) {

        var requestBuilder = Glide.with(imageView!!)
            .load(imageView.context.parseTmdbResourcePath(url))

        if (placeholder != null) {
            requestBuilder = requestBuilder
                .placeholder(placeholder)
        }

        requestBuilder
            .into(imageView)

    }
}