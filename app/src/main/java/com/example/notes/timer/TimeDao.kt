package com.example.notes.timer

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TimeDao {

    @Insert
    suspend fun insert(time: TimeUsed)


    @Update
    suspend fun update(time: TimeUsed)

    @Query("SELECT * FROM Time_Used")
    fun getAlltime(): LiveData<List<TimeUsed>>

    @Query("DELETE FROM time_used")
    suspend fun clear()


    @Query("SELECT * FROM time_used ORDER BY usedId DESC LIMIT 1")
    suspend fun getThisTime(): TimeUsed?
}