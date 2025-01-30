package org.example.http

import dagger.Module
import dagger.Provides
import org.example.api.usecases.*
import org.example.http.tasks.*

@Module
class HttpModule {

    @Provides
    fun provideCreateTaskHttpRequest(createTaskUseCase: CreateTaskUseCase): CreateTaskHttpRequest =
        CreateTaskHttpRequest(createTaskUseCase)

    @Provides
    fun provideGetTaskByNameHttpRequest(getTaskByNameUseCase: GetTaskByNameUseCase): GetTaskByNameHttpRequest =
        GetTaskByNameHttpRequest(getTaskByNameUseCase)

    @Provides
    fun provideGetAllTasksHttpRequest(getAllTasksUseCase: GetAllTasksUseCase): GetAllTasksHttpRequest =
        GetAllTasksHttpRequest(getAllTasksUseCase)

    @Provides
    fun provideUpdateTaskHttpRequest(updateTaskUseCase: UpdateTaskUseCase): UpdateTaskHttpRequest =
        UpdateTaskHttpRequest(updateTaskUseCase)

    @Provides
    fun provideDeleteTaskHttpRequest(deleteTaskUseCase: DeleteTaskUseCase): DeleteTaskHttpRequest =
        DeleteTaskHttpRequest(deleteTaskUseCase)
}
