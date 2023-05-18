package com.example.notesapp.database.dao

import android.accounts.AuthenticatorDescription
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.notesapp.database.entity.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(note: Note)

    @Query("select * from Notes")
    fun getNotesLive(): LiveData<List<Note>>

    @Query("select * from Notes")
    suspend fun getNotes(): List<Note>


    @Query("update Notes set noteStatus=:noteStatus where noteId=:noteId")
    suspend fun updateNoteStatus(noteId: Int, noteStatus: Int)

    @Query("update Notes set noteTitle=:title,noteDescription=:description,noteTag=:categoryList where noteId=:noteId")
    suspend fun updateNote(
        noteId: Int,
        title: String,
        description: String,
        categoryList: ArrayList<String>
    )

}