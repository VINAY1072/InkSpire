package com.example.inkspire.di

import android.app.Application
import androidx.room.Room
import com.example.inkspire.InkSpireApplication
import com.example.inkspire.data.datasource.NoteDB
import com.example.inkspire.data.repository.NoteRepositoryImpl
import com.example.inkspire.domain.repository.NoteRepository
import com.example.inkspire.domain.usecase.DeleteNoteUseCase
import com.example.inkspire.domain.usecase.GetNoteByIdUseCase
import com.example.inkspire.domain.usecase.GetNotesUseCase
import com.example.inkspire.domain.usecase.InsertUseCase
import com.example.inkspire.domain.usecase.NotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNotesDataBase(app: Application): NoteDB {
        return Room.databaseBuilder(
            app,
            NoteDB::class.java,
            NoteDB.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesNoteRepository(db: NoteDB): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun providesNotesUseCase(repository: NoteRepository) : NotesUseCase {
        return NotesUseCase(
            getNotesUseCase = GetNotesUseCase(repository),
            getNoteByIdUseCase = GetNoteByIdUseCase(repository),
            insertUseCase = InsertUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository)
        )
    }

}