package org.example.data.repository

import org.example.domain.repository.TaskRepository
import org.example.domain.entities.Task
import org.example.domain.entities.Priority
import org.example.data.db.TaskTable
import org.example.data.db.suspendTransaction
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class PostgresTaskRepository : TaskRepository {
    override suspend fun allTasks(): List<Task> = suspendTransaction {
        TaskTable.selectAll().map {
            Task(
                name = it[TaskTable.name],
                description = it[TaskTable.description],
                priority = Priority.valueOf(it[TaskTable.priority])
            )
        }
    }

    override suspend fun taskByName(name: String): Task? = suspendTransaction {
        val query = TaskTable.selectAll().filter { it[TaskTable.name] == name }
        query.map {
            Task(
                name = it[TaskTable.name],
                description = it[TaskTable.description],
                priority = Priority.valueOf(it[TaskTable.priority])
            )
        }.firstOrNull()
    }


    override suspend fun addTask(task: Task): Unit = suspendTransaction {
        TaskTable.insert {
            it[name] = task.name
            it[description] = task.description
            it[priority] = task.priority.toString()
        }
    }

    override suspend fun removeTask(name: String): Boolean = suspendTransaction {
        val rowsDeleted = TaskTable.deleteWhere { TaskTable.name eq name }
        rowsDeleted > 0
    }
}
