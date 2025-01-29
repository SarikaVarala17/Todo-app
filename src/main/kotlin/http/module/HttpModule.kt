package org.example.http

import dagger.Module
import dagger.Provides
import org.example.api.usecases.CreateTaskUseCase
import org.example.api.usecases.GetTaskByNameUseCase
import org.example.api.usecases.GetAllTasksUseCase
import org.example.api.usecases.UpdateTaskUseCase
import org.example.api.usecases.DeleteTaskUseCase
import org.example.http.components.DefaultHttpComponent
import org.example.http.components.HttpComponent
import org.example.http.tasks.CreateTaskHttpRequest
import org.example.http.tasks.GetTaskByNameHttpRequest
import org.example.http.tasks.GetAllTasksHttpRequest
import org.example.http.tasks.UpdateTaskHttpRequest
import org.example.http.tasks.DeleteTaskHttpRequest

@Module
class HttpModule {

    @Provides
    fun provideCreateTaskHttpRequest(createTaskUseCase: CreateTaskUseCase): CreateTaskHttpRequest {
        return CreateTaskHttpRequest(createTaskUseCase)
    }

    @Provides
    fun provideGetTaskByNameHttpRequest(getTaskByNameUseCase: GetTaskByNameUseCase): GetTaskByNameHttpRequest {
        return GetTaskByNameHttpRequest(getTaskByNameUseCase)
    }

    @Provides
    fun provideGetAllTasksHttpRequest(getAllTasksUseCase: GetAllTasksUseCase): GetAllTasksHttpRequest {
        return GetAllTasksHttpRequest(getAllTasksUseCase)
    }

    @Provides
    fun provideUpdateTaskHttpRequest(updateTaskUseCase: UpdateTaskUseCase): UpdateTaskHttpRequest {
        return UpdateTaskHttpRequest(updateTaskUseCase)
    }

    @Provides
    fun provideDeleteTaskHttpRequest(deleteTaskUseCase: DeleteTaskUseCase): DeleteTaskHttpRequest {
        return DeleteTaskHttpRequest(deleteTaskUseCase)
    }

    @Provides
    fun provideHttpComponent(
        createTaskHttpRequest: CreateTaskHttpRequest,
        getTaskByNameHttpRequest: GetTaskByNameHttpRequest,
        getAllTasksHttpRequest: GetAllTasksHttpRequest,
        updateTaskHttpRequest: UpdateTaskHttpRequest,
        deleteTaskHttpRequest: DeleteTaskHttpRequest
    ): HttpComponent {
        return DefaultHttpComponent(
            createTaskHttpRequest,
            getTaskByNameHttpRequest,
            getAllTasksHttpRequest,
            updateTaskHttpRequest,
            deleteTaskHttpRequest
        )
    }
}
