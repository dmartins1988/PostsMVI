package com.example.postsmvi.di

import android.app.Application
import androidx.room.Room
import com.example.postsmvi.data.database.PostDao
import com.example.postsmvi.data.database.PostDatabase
import org.koin.dsl.module

fun providePostDatabase(application: Application): PostDatabase =
    Room.databaseBuilder(
        application,
        PostDatabase::class.java,
        PostDatabase.DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

fun provideDao(postDatabase: PostDatabase): PostDao = postDatabase.postDao

val databaseModule = module {
    single { providePostDatabase(get()) }
    single { provideDao(get()) }
}