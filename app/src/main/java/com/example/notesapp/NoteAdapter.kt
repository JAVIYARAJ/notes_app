package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.models.Notes

class NoteAdapter(var noteList: ArrayList<Notes>, var context: Context) :
    RecyclerView.Adapter<NoteAdapter.MyNoteViewModel>() {
    class MyNoteViewModel(view: View) : RecyclerView.ViewHolder(view) {

        var noteTitle = view.findViewById<TextView>(R.id.notesTitle)
        var noteDescription = view.findViewById<TextView>(R.id.notesDescription)
        var noteCategory = view.findViewById<TextView>(R.id.noteCategory)
        var notePublishedDate = view.findViewById<TextView>(R.id.notesPublishedDate)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNoteViewModel {
        var view =
            LayoutInflater.from(context).inflate(R.layout.custom_notes_layout, parent, false)
        return MyNoteViewModel(view)
    }

    override fun onBindViewHolder(holder: MyNoteViewModel, position: Int) {
        var item = noteList[position]


        holder.noteTitle.text = item.title
        holder.noteDescription.text = item.description
        holder.noteCategory.text = item.category.toString()
        holder.notePublishedDate.text = item.date;
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}