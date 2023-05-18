package com.example.notesapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.adapter.NoteAdapter
import com.example.notesapp.R
import com.example.notesapp.database.entity.Note
import com.example.notesapp.database.helper.NoteDb
import com.example.notesapp.models.Notes
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment(var activity: AppCompatActivity) : Fragment() {

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.notesRecyclerView);

        val data = ArrayList<Note>();

        val noteDao = NoteDb.getInstance(requireContext().applicationContext).noteDao();
        GlobalScope.launch {
            val response = noteDao.getNotes()
            data.addAll(response);
        }
        val noteAdapter =
            NoteAdapter(noteList = data, context = requireContext().applicationContext,activity)
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.adapter = noteAdapter;
        noteAdapter.notifyDataSetChanged();

        return view;
    }

}