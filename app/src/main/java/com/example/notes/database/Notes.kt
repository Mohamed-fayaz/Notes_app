package com.example.notes.database
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes_Table")
data class Notes(@PrimaryKey @ColumnInfo(name = "id") var id: Int? =null,
    @ColumnInfo(name = "title") var title: String? =null,
@ColumnInfo(name = "description") var description: String? =null)