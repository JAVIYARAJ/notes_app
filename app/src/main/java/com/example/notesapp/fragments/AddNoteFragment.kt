package com.example.notesapp.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.adapter.AddCategoryAdapter
import com.example.notesapp.adapter.NotePdfAdapter
import com.example.notesapp.database.helper.NoteDb
import com.example.notesapp.models.NoteDocument
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

class AddNoteFragment(
    var activity: AppCompatActivity,
    var id: Int? = null,
    var note: com.example.notesapp.database.entity.Note? = null
) : Fragment() {

    lateinit var listOfCategories: ArrayList<String>

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_note, container, false)

        //initialize views
        val crudNoteBackBtn = view.findViewById<ImageView>(R.id.crudNoteBackBtn);
        val notesTitleText = view.findViewById<EditText>(R.id.notesTitleText);
        val notesDescriptionText = view.findViewById<EditText>(R.id.notesDescriptionText);
        val taskCategory = view.findViewById<Spinner>(R.id.taskCategory)
        val saveNoteBtn = view.findViewById<ImageView>(R.id.saveNoteBtn)
        val addCategoryRecyclerview = view.findViewById<RecyclerView>(R.id.addCategoryRecyclerview)
        val noteDocumentsRecyclerview =
            view.findViewById<RecyclerView>(R.id.noteDocumentsRecyclerview)

        //back button
        crudNoteBackBtn.setOnClickListener {
            activity.supportFragmentManager.popBackStack()
        }

        //initialize drop down adapter
        val dropdownAdapter = ArrayAdapter<String>(
            requireContext().applicationContext,
            R.layout.custom_dropdown_layout,
            arrayOf("Home", "Education", "Grocery", "Others")
        )
        taskCategory.adapter = dropdownAdapter


        //below code for edit data
        if (id != null) {
            val screenTitle = view.findViewById<TextView>(R.id.crud_screen_title);

            //change title
            screenTitle.text = "Edit Note"

            listOfCategories = ArrayList<String>();
            listOfCategories.addAll(note!!.noteTag)

            notesTitleText.setText(note!!.noteTitle)
            notesDescriptionText.setText(note!!.noteDescription)

            saveNoteBtn.setOnClickListener {
                if (notesTitleText.text.toString()
                        .isNotEmpty() && notesDescriptionText.text.toString().isNotEmpty()
                ) {
                    //add notes in room db
                    val noteDao = NoteDb.getInstance(requireContext().applicationContext).noteDao();
                    GlobalScope.launch {
                        noteDao.updateNote(
                            note!!.noteId,
                            notesTitleText.text.toString(),
                            notesDescriptionText.text.toString(),
                            listOfCategories
                        )
                    }
                } else {
                    Toast.makeText(
                        requireContext().applicationContext,
                        "Please enter all values",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        } else {

            listOfCategories = ArrayList<String>()
            listOfCategories.add("Home")

            val listOfDocuments = ArrayList<NoteDocument>()

            with(listOfDocuments) {
                this.add(NoteDocument("0", "doc1", "/desktop/new/doc/doc1.pdf", true))
                this.add(NoteDocument("0", "doc1", "/desktop/new/doc/doc1.pdf", false))
                this.add(NoteDocument("0", "doc1", "/desktop/new/doc/doc1.pdf", false))
                this.add(NoteDocument("0", "doc1", "/desktop/new/doc/doc1.pdf", false))
                this.add(NoteDocument("0", "doc1", "/desktop/new/doc/doc1.pdf", false))
            }

            //save note
            saveNoteBtn.setOnClickListener {
                if (notesTitleText.text.toString()
                        .isNotEmpty() && notesDescriptionText.text.toString().isNotEmpty()
                ) {
                    //add notes in room db
                    val noteDao = NoteDb.getInstance(requireContext().applicationContext).noteDao();
                    GlobalScope.launch {
                        noteDao.addNote(
                            com.example.notesapp.database.entity.Note(
                                0,
                                noteTitle = notesTitleText.text.toString(),
                                noteDescription = notesDescriptionText.text.toString(),
                                noteTag = listOfCategories,
                                createdDate = Date(),
                                noteStatus = 0
                            )
                        )
                    }
                } else {
                    Toast.makeText(
                        requireContext().applicationContext,
                        "Please enter all values",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            //for notes document
            noteDocumentsRecyclerview.adapter =
                NotePdfAdapter(listOfDocuments, requireContext().applicationContext)
            noteDocumentsRecyclerview.layoutManager =
                GridLayoutManager(requireContext().applicationContext, 4)

        }


        val addCategoryAdapter =
            AddCategoryAdapter(listOfCategories, requireContext().applicationContext)
        addCategoryRecyclerview.layoutManager = LinearLayoutManager(
            requireContext().applicationContext, LinearLayoutManager.HORIZONTAL, false
        )

        addCategoryRecyclerview.adapter = addCategoryAdapter

        //perform onclick event on drop down
        taskCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                val itemText = parent?.getItemAtPosition(position).toString()

                if (!listOfCategories.contains(itemText)) {
                    addCategoryAdapter.addItem(itemText)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        return view
    }

    //create activity result contract
    private val contract = object : ActivityResultContract<Intent, Intent>() {

        override fun createIntent(context: Context, input: Intent): Intent {
            return input
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Intent {
            return intent!!
        }

    }

}