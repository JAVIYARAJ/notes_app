package com.example.notesapp.models

data class NoteDocument(
    var docId: String, var name: String, var path: String, var isThumbnail: Boolean
)