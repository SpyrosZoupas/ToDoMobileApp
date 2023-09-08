package com.leedsbeckett.todo_application.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize //allows to pass objects between activities or fragments
@Entity(tableName = "task_table") // Represents a table called "task_table"
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name="name")
    val name: String,
    @ColumnInfo(name="description")
    val description: String
): Parcelable