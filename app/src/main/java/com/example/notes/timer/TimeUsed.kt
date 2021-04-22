package com.example.notes.timer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Time_Used")
data class TimeUsed(
    @PrimaryKey(autoGenerate = true)
    var usedId: Long = 0L,

    @ColumnInfo(name = "start_time_milli")
    var startTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_time_milli")
    var endTimeMilli: Long = System.currentTimeMillis(),

)