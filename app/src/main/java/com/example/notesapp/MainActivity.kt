package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.models.Notes

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       var recyclerView = findViewById<RecyclerView>(R.id.notesRecyclerView);

        var data = ArrayList<Notes>();
        with(data) {
            add(
                Notes(
                    1,
                    "Retrofit with MVVM",
                    "Retrofit is use to get json data from api or database.",
                    arrayListOf<String>("Success", "Goals"),
                    "20/02/2023"
                )
            )
            add(
                Notes(
                    2,
                    "Volley with MVVM",
                    "Volley is use to get json data from api or database.",
                    arrayListOf<String>("Success", "Goals"),
                    "20/02/2023"
                )
            )
            add(
                Notes(
                    2,
                    "Fragment",
                    "Fragment is use to make design reusable.",
                    arrayListOf<String>("IT", "Education"),
                    "20/02/2023"
                )
            )
            add(
                Notes(
                    2,
                    "Rest Api",
                    "RESTful API is an interface that two computer systems use to exchange information securely over the internet.",
                    arrayListOf<String>("IT", "Education"),
                    "20/02/2023"
                )
            )
        }

        var noteAdapter=NoteAdapter(noteList = data, context = applicationContext)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=noteAdapter;
        noteAdapter.notifyDataSetChanged();

    }
}