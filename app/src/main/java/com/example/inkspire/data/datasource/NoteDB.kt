package com.example.inkspire.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.inkspire.domain.models.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDB: RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "note_db"
    }

}