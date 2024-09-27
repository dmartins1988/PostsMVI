package com.example.postsmvi.di

import com.example.postsmvi.presentation.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::PostsViewModel)
}