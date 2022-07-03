package com.tmdb.domain.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aaa.clean.vm.CleanViewModel
import com.tmdb.domain.content.data.request.ContentRequest
import com.tmdb.domain.content.data.request.MIN_TMDB_API_PAGE
import com.tmdb.domain.content.data.response.CarouselList
import com.tmdb.domain.content.usecases.ContentUseCase
import com.tmdb.domain.ext.updateValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class ContentViewModel @Inject internal constructor(
    private val contentUseCaseProvider: Provider<ContentUseCase>
): CleanViewModel() {

    private lateinit var contentUseCase: ContentUseCase

    fun searchContent(
        query: String,
        adultContent: Boolean = false,
        page: Int = MIN_TMDB_API_PAGE
    ) {
        contentUseCase = contentUseCaseProvider.get()

        viewModelScope.launch {

            contentUseCase.fetchContent(createRequest(
                query = query,
                adultContent = adultContent,
                page = page
            )).onEach { carousels ->
                _carousels.updateValue(carousels as MutableList<CarouselList>)
            }.catch { error ->
                //  TODO: Propagate exceptions here.
                Log.e(TAG, error.message, error)
            }.collect()
        }

    }

    private fun createRequest(
        query: String,
        adultContent: Boolean,
        page: Int
    ): ContentRequest {
        return ContentRequest(
            query = query,
            adultContent = adultContent
        ).apply {
            this.page = page
        }
    }

    private val _carousels = MutableLiveData<MutableList<CarouselList>>()
    val carousels: LiveData<MutableList<CarouselList>> get() = _carousels

    companion object {
        const val TAG = "ContentViewModel"
    }
}