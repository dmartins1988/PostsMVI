package com.example.postsmvi

import android.app.Application
import com.example.postsmvi.di.databaseModule
import com.example.postsmvi.di.repositoryModule
import com.example.postsmvi.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PostApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PostApplication)
            androidLogger()
            modules(
                databaseModule,
                viewModelModule,
                repositoryModule
            )
        }
    }
}