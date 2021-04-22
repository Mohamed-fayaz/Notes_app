package com.example.notes.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class NotesRepository(private val notesDao: NotesDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: LiveData<List<Notes>> = notesDao.getAllNotes()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(note: Notes) {
        notesDao.insert(note)

    }
    suspend fun deleteall(){
        notesDao.deleteAll()
    }
}