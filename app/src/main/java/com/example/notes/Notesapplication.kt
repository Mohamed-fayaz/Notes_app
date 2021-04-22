package com.example.notes

import android.app.Application
import com.example.notes.database.NotesRepository
import com.example.notes.database.NotesRoomDatabase
import com.example.notes.timer.TimeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Notesapplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { NotesRoomDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { NotesRepository(database.notesDao()) }
    val timeRepository by lazy { TimeRepository(database.timeDao()) }
}