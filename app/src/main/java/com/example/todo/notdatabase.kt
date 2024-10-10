package com.example.todo

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [Note::class], version = 1)

abstract class notdatabase : RoomDatabase() {

    abstract fun getNoteDao() :Notedao
}