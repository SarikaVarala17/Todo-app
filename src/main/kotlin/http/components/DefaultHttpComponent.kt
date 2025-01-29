package org.example.http.components

import org.example.http.tasks.*

class DefaultHttpComponent(
    override val createTaskHttpRequest: CreateTaskHttpRequest,
    override val getTaskByNameHttpRequest: GetTaskByNameHttpRequest,
    override val getAllTaskHttpRequest: GetAllTasksHttpRequest,
    override val updateTaskHttpRequest: UpdateTaskHttpRequest,
    override val deleteTaskHttpRequest: DeleteTaskHttpRequest
) : HttpComponent
