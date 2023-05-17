package com.example.notesapp.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.PracticeActivity
import com.example.notesapp.R
import com.example.notesapp.models.NoteCategoryList
import com.example.notesapp.models.NoteDocument

class NotePdfAdapter(
    var noteDocumentsList: ArrayList<NoteDocument>,
    var context: Context,
    var activity: Activity
) :
    RecyclerView.Adapter<NotePdfAdapter.MyNoteViewModel>() {
    class MyNoteViewModel(view: View) : RecyclerView.ViewHolder(view) {
        val noteDocumentsIcon: ImageView = view.findViewById<ImageView>(R.id.noteDocumentsIcon);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNoteViewModel {
        val view =
            LayoutInflater.from(context).inflate(R.layout.custom_pdf_note_layout, parent, false)
        return MyNoteViewModel(view)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: MyNoteViewModel, position: Int) {
        val item = noteDocumentsList[position]
        if (item.isThumbnail) {
            val image = context.resources.getDrawable(R.drawable.ic_file_thumbnail_icon, null)
            holder.noteDocumentsIcon.setImageDrawable(image);
            holder.noteDocumentsIcon.setOnClickListener {
                var intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "application/pdf"
                intent = Intent.createChooser(intent, "Select a pdf")
            }
        } else {
            val image = context.resources.getDrawable(R.drawable.ic_file_icon, null)
            holder.noteDocumentsIcon.setImageDrawable(image);
        }
    }

    override fun getItemCount(): Int {
        return noteDocumentsList.size
    }

    //create activity result contract
    val contract = object : ActivityResultContract<Intent, Intent>() {

        override fun createIntent(context: Context, input: Intent): Intent {
            return input
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Intent {
            return intent!!
        }

    }

    //create launcher


}