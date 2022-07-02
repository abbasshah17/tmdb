package com.tmdb.domain.content

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aaa.clean.vm.CleanViewModel
import com.tmdb.domain.content.data.response.Carousel
import com.tmdb.domain.content.usecases.ContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject internal constructor(
    private val contentUseCase: ContentUseCase
): CleanViewModel() {
    private val _carousels = MutableLiveData<List<Carousel>>()
    val carousels: LiveData<List<Carousel>> get() = _carousels

}