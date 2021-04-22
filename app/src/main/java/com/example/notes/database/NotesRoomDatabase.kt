package com.example.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.notes.timer.TimeDao
import com.example.notes.timer.TimeUsed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Notes::class, TimeUsed::class), version = 1, exportSchema = false)
abstract class NotesRoomDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao
    abstract fun timeDao(): TimeDao

    private class NotesDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var notesDao = database.notesDao()
                    var timeDao = database.timeDao()
                    // Delete all content here.
                    notesDao.deleteAll()
                    timeDao.clear()

                    // Add sample wo
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: NotesRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): NotesRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesRoomDatabase::class.java,
                    "Notes_Database"
                )
                    .addCallback(NotesDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}