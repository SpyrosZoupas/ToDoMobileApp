package com.leedsbeckett.todo_application.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.leedsbeckett.todo_application.data.TaskDatabase
import com.leedsbeckett.todo_application.model.Task
import com.leedsbeckett.todo_application.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// ViewModel class responsible for managing data for the UI.
// Extends AndroidViewModel to provide application context.
// Most functions run on a background thread using Coroutines and Dispatchers.IO
class TaskViewModel(application: Application): AndroidViewModel(application) {

    // LiveData that holds a list of all tasks from the database.
    val readAllData: LiveData<List<Task>>

    // Instance of TaskRepository used for CRUD operations
    private val repository: TaskRepository

    // Initialise taskDao and TaskRepository objects
    // readAllData reads all task rows from database
    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        readAllData = repository.readAllTasks
    }

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }
}