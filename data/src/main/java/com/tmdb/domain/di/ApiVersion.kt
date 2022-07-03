package com.tmdb.domain.di

import javax.inject.Qualifier

@Qualifier
@kotlin.annotation.Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
annotation class ApiVersion
