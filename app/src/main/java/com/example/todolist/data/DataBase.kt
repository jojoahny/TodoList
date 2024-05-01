package com.example.todolist.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskItem::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun DaoTaskItem(): DaoTasks
}