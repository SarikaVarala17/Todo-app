package org.example.data.db

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.example.domain.entities.Priority
import org.example.domain.entities.Task


object TaskTable : Table("task") {
    val id = integer("id").autoIncrement() // Explicit primary key
    val name = varchar("name", 50)
    val description = varchar("description", 50)
    val priority = varchar("priority", 50)

    override val primaryKey = PrimaryKey(id) // Mark 'id' as the primary key
}

suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
    newSuspendedTransaction(Dispatchers.IO, statement = block)

fun rowToModel(row: ResultRow): Task = Task(
    name = row[TaskTable.name],
    description = row[TaskTable.description],
    priority = Priority.valueOf(row[TaskTable.priority])
)

