package com.example.notesapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.adapter.NoteAdapter
import com.example.notesapp.R
import com.example.notesapp.models.Notes

class HomeFragment : Fragment() {

    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.notesRecyclerView);

        val data = ArrayList<Notes>();

        with(data) {
            add(
                Notes(
                    1,
                    "Retrofit with MVVM",
                    "Retrofit is use to get json data from api or database.",
                    "Goals",
                    "20/02/2023"
                )
            )
            add(
                Notes(
                    2,
                    "Volley with MVVM",
                    "Volley is use to get json data from api or database.",
                    "Study",
                    "20/02/2023"
                )
            )
            add(
                Notes(
                    2,
                    "Fragment",
                    "Fragment is use to make design reusable.",
                    "IT",
                    "20/02/2023"
                )
            )
            add(
                Notes(
                    2,
                    "Rest Api",
                    "RESTful API is an interface that two computer systems use to exchange information securely over the internet.",
                    "IT",
                    "20/02/2023"
                )
            )
            add(
                Notes(
                    2,
                    "Rest Api",
                    "RESTful API is an interface that two computer systems use to exchange information securely over the internet.",
                    "IT",
                    "20/02/2023"
                )
            )
        }



        val noteAdapter =
            NoteAdapter(noteList = data, context = requireContext().applicationContext)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.adapter = noteAdapter;
        noteAdapter.notifyDataSetChanged();

        return view;
    }

}