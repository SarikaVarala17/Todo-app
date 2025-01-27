package org.examnple.data.di

import org.example.data.repository.PostgresTaskRepository
import org.example.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule{

    @Provides
    @Singleton
    fun providesTaskRepository(): TaskRepository {
        return PostgresTaskRepository()
    }
}