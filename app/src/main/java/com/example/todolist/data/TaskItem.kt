package com.example.todolist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "Tasks")
data class TaskItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="task_id") val id: Int = 0,
    @ColumnInfo(name ="task_title") var title: String,
    @ColumnInfo(name ="task_description") var description: String,
    @ColumnInfo(name ="task_date") var taskDate: Long
) {
    fun getDay(): String {
        return SimpleDateFormat("EEEE").format(Date(taskDate))
    }

    fun getDate(): String {
        return SimpleDateFormat("dd").format(Date(taskDate))
    }

    // get first 3 chars of the month
    fun getMonth(): String {
        return SimpleDateFormat("MMMM").format(Date(taskDate))
    }

    fun getFullDateString(): String {
        val format = SimpleDateFormat("MMM dd, yyyy", Locale.US)
        return format.format(Date(taskDate))
    }

}