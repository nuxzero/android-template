package com.example.app.di

import android.content.Context
import com.example.app.NotesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        NetworkModule::class,
    ]
)
interface NotesApplicationComponent : AndroidInjector<NotesApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): NotesApplicationComponent
    }
}
