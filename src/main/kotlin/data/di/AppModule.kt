package org.example.data.di

import dagger.Module
import dagger.Provides
import org.example.data.repository.PostgresTaskRepository
import org.example.domain.repository.TaskRepository
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun providesTaskRepository(postgresTaskRepository: PostgresTaskRepository): TaskRepository {
        return postgresTaskRepository
    }
}
