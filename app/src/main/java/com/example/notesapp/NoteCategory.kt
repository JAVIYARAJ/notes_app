package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.models.NoteCategoryList
import com.example.notesapp.models.Notes

class NoteCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_category)

        var recyclerView = findViewById<RecyclerView>(R.id.noteCategoryRecyclerView);

        var data = ArrayList<NoteCategoryList>();
        with(data) {
            add(
                NoteCategoryList(
                    1,
                    "Education",
                    10,
                )
            )
            add(
                NoteCategoryList(
                    1,
                    "Goals",
                    10,
                )
            )
        }

        var noteAdapter=NoteCategoryAdapter(noteCategoryList = data, context = applicationContext)
        recyclerView.layoutManager= GridLayoutManager(applicationContext,2,)
        recyclerView.adapter=noteAdapter;
        noteAdapter.notifyDataSetChanged();
    }
}