package com.example.tasksapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("domain")
    val domain: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("createdTime")
    val createdTime: Long,
    @SerializedName("priority")
    val priority: Priority,
    @SerializedName("deadline")
    val deadline: Long,
    @SerializedName("status")
    val status: Status,
    @SerializedName("progress")
    val progress: Int
)
