package com.example.todolist.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DaoTasks {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(taskItem: TaskItem)
    @Query("SELECT * FROM Tasks")
    fun getTasks(): List<TaskItem>
    @Query("SELECT * FROM Tasks WHERE task_id=:t_id")
    fun selectTaskByID(t_id:Int): TaskItem
    @Query("DELETE FROM Tasks WHERE task_id=:t_id")
    fun deleteTaskByID(t_id:Int)
}