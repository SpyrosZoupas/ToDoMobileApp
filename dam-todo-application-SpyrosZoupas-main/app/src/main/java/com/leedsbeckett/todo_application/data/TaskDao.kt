package com.leedsbeckett.todo_application.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.leedsbeckett.todo_application.model.Task

// Defines CRUD functions for accessing and modifying data in "task_table".
@Dao
interface TaskDao
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("SELECT * FROM task_table")
    fun readAllTasks(): LiveData<List<Task>>
}