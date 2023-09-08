package com.leedsbeckett.todo_application.repository

import androidx.lifecycle.LiveData
import com.leedsbeckett.todo_application.data.TaskDao
import com.leedsbeckett.todo_application.model.Task

// TaskRepository is an intermediary between TaskViewModel and TaskDao
// Calls CRUD functions using instance of TaskDao
class TaskRepository(private val taskDao: TaskDao)
{
    val readAllTasks: LiveData<List<Task>> = taskDao.readAllTasks()

    fun addTask(task: Task) {
        taskDao.addTask(task)
    }

    fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }
}