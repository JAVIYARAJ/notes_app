package com.example.notesapp.models

data class Notes(
    var id: Int,
    var title: String,
    var description: String,
    var category: ArrayList<String>,
    var date: String
)
