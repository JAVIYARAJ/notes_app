package com.example.notesapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var noteId: Int,
    var noteTitle: String,
    var noteDescription: String,
    var noteTag: ArrayList<String>,
    var createdDate: Date,
    var noteStatus: Int,
    )