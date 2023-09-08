package com.leedsbeckett.todo_application.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leedsbeckett.todo_application.R
import com.leedsbeckett.todo_application.model.Task

// Define database entity using Room library
// Class is abstract since it will not be instantiated directly
@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase: RoomDatabase()
{
    abstract fun taskDao(): TaskDao

    // Companion object implements Singleton pattern,
    // Ensures only one instance of the database is created
    companion object {
        // Database instance is volatile making it visible across different threads
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        //Get database instance
        fun getDatabase(context: Context): TaskDatabase {
            // Check if instance already exists and return instance if true
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            // Else create database instance using Room library
            // Synchronise object making it thread-safe
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    context.getString(R.string.task_database)
                ).build()
                // Assign created instance in INSTANCE object
                INSTANCE = instance
                return instance
            }
        }
    }
}