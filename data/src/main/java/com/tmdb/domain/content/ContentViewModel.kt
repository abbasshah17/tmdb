package com.tmdb.domain.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aaa.clean.vm.CleanViewModel
import com.tmdb.domain.content.data.response.Carousel

class ContentViewModel: CleanViewModel() {
    private val _carousels = MutableLiveData<List<Carousel>>()
    val carousels: LiveData<List<Carousel>> get() = _carousels

}