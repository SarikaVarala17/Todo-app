package org.example.domain.di

import org.example.domain.repository.TaskRepository
import org.examnple.data.di.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules=[AppModule::class])
interface AppComponent {
    fun getTaskRepository(): TaskRepository
}