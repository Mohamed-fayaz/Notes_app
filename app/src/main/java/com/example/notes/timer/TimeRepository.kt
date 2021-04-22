package com.example.notes.timer

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class TimeRepository(private val timeDao: TimeDao) {

    // Room executes all queries on a separate thread.
    // Observed Live data  will notify the observer when the data has changed.
    val alltimes : LiveData<List<TimeUsed>> = timeDao.getAlltime()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(time: TimeUsed) {
        timeDao.insert(time)

    }
    suspend fun clear(){
        timeDao.clear()
    }
    suspend fun getThistime() : TimeUsed?{
        return timeDao.getThisTime()
    }
    suspend fun update(time: TimeUsed){
        timeDao.update(time)
    }
}