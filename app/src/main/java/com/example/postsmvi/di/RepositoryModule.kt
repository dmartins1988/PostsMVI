package com.example.postsmvi.di

import com.example.postsmvi.data.repository.PostsRepositoryImpl
import com.example.postsmvi.domain.repository.PostsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<PostsRepository> { PostsRepositoryImpl(get()) }
}