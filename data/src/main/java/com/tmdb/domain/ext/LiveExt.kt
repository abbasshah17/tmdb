package com.tmdb.domain.ext

import android.os.Looper
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.updateValue(value: T?) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        this.value = value
    } else {
        postValue(value)
    }
}
