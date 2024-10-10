package com.example.todo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class Note(

    @PrimaryKey(autoGenerate = true)

    val id: Int = 0,

    val title: String,
    val title2: String,
    val time: String,
    val date: String
)
