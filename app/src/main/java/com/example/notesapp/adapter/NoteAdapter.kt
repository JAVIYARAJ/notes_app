package com.example.notesapp.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.database.entity.Note
import com.example.notesapp.database.helper.NoteDb
import com.example.notesapp.fragments.AddNoteFragment
import com.example.notesapp.models.Notes
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class NoteAdapter(
    var noteList: ArrayList<Note>,
    var context: Context,
    var activity: AppCompatActivity
) :
    RecyclerView.Adapter<NoteAdapter.MyNoteViewModel>() {
    class MyNoteViewModel(view: View) : RecyclerView.ViewHolder(view) {

        var noteTitle: TextView = view.findViewById<TextView>(R.id.notesTitle)
        var noteDescription: TextView = view.findViewById<TextView>(R.id.notesDescription)
        var noteCategory: TextView = view.findViewById<TextView>(R.id.noteCategory)
        var notePublishedDate: TextView = view.findViewById<TextView>(R.id.notesPublishedDate)
        var noteStatus: CheckBox = view.findViewById(R.id.noteStatusCheckbox)
        var noteCard: LinearLayoutCompat = view.findViewById(R.id.noteCard)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNoteViewModel {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_notes_layout, parent, false)
        return MyNoteViewModel(view)
    }

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("SimpleDateFormat", "ResourceType")
    override fun onBindViewHolder(holder: MyNoteViewModel, position: Int) {
        val item = noteList[position]

        holder.noteTitle.text = item.noteTitle
        holder.noteDescription.text = item.noteDescription
        holder.noteCategory.text = item.noteTag[0][0].uppercase().toString()

        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm a")
        holder.notePublishedDate.text = sdf.format(item.createdDate);

        if (item.noteStatus == 1) {
            holder.noteStatus.isChecked = true
        }

        holder.noteStatus.setOnClickListener {
            if (item.noteStatus == 1) {
                GlobalScope.launch {
                    NoteDb.getInstance(context).noteDao().updateNoteStatus(item.noteId, 0)
                }
            } else {
                GlobalScope.launch {
                    NoteDb.getInstance(context).noteDao().updateNoteStatus(item.noteId, 1)
                }
            }
        }

        holder.noteCard.setOnClickListener {
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.notesFrameLayout, AddNoteFragment(activity, id = item.noteId,item))
                .addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}