package com.example.tasksapplication.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tasksapplication.model.Task
import io.reactivex.Observable

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks ORDER BY title ASC")
    fun getAll(): Observable<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(tasks: List<Task>)

    @Query("DELETE FROM tasks WHERE id =:taskId")
    fun delete(taskId: Int)

    @Update
    fun update(task: Task)

    @Query("DELETE FROM tasks")
    fun deleteAll()

    @Query("SELECT * FROM tasks WHERE id =:taskId LIMIT 1")
    fun getById(taskId: Int): LiveData<Task>

    @Query("DELETE FROM tasks WHERE id NOT IN (:tasksIds)")
    fun deleteIfNotPresent(tasksIds: List<Int>)
}