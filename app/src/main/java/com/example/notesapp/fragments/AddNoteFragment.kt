package com.example.notesapp.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
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
import com.example.notesapp.models.NoteDocument

class AddNoteFragment(var activity: AppCompatActivity) : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_note, container, false)

        val listOfCategories = ArrayList<String>()
        listOfCategories.add("Home")
        listOfCategories.add("Education")

        val listOfDocuments = ArrayList<NoteDocument>()

        with(listOfDocuments) {
            this.add(NoteDocument("0", "doc1", "/desktop/new/doc/doc1.pdf", true))
        }

        val taskCategory = view.findViewById<Spinner>(R.id.taskCategory)
        val saveNoteBtn = view.findViewById<ImageView>(R.id.saveNoteBtn)
        val chooseDocumentBtn = view.findViewById<TextView>(R.id.chooseDocument)


        val addCategoryRecyclerview = view.findViewById<RecyclerView>(R.id.addCategoryRecyclerview)
        val noteDocumentsRecyclerview =
            view.findViewById<RecyclerView>(R.id.noteDocumentsRecyclerview)

        val notesTitleText = view.findViewById<EditText>(R.id.notesTitleText);
        val notesDescriptionText = view.findViewById<EditText>(R.id.notesDescriptionText);

        val dropdownAdapter = ArrayAdapter<String>(
            requireContext().applicationContext, R.layout.custom_dropdown_layout,
            arrayOf("Home", "Education", "Grocery", "Others")
        )

        val addCategoryAdapter =
            AddCategoryAdapter(listOfCategories, requireContext().applicationContext)
        addCategoryRecyclerview.layoutManager =
            LinearLayoutManager(
                requireContext().applicationContext,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        addCategoryRecyclerview.adapter = addCategoryAdapter

        taskCategory.adapter = dropdownAdapter

        taskCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val itemText = parent?.getItemAtPosition(position).toString()

                if (!listOfCategories.contains(itemText)) {
                    addCategoryAdapter.addItem(itemText)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        val categoryListContainer =
            view.findViewById<LinearLayoutCompat>(R.id.categoryListContainer)
        val addCategoryWarningMessage = view.findViewById<TextView>(R.id.addCategoryWarningMessage)

        if (addCategoryAdapter.categoryList.isEmpty()) {
            categoryListContainer.visibility = View.GONE
            addCategoryWarningMessage.visibility = View.VISIBLE
        } else {
            categoryListContainer.visibility = View.VISIBLE
            addCategoryWarningMessage.visibility = View.GONE
        }


        //save note
        saveNoteBtn.setOnClickListener {
            if (notesTitleText.text.toString().isNotEmpty() && notesDescriptionText.text.toString()
                    .isNotEmpty()
            ) {

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
            GridLayoutManager(requireContext().applicationContext, 3)

        var intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "application/pdf"
        intent = Intent.createChooser(intent, "Select a pdf")

        //create launcher
        val launcher = activity.registerForActivityResult(
            contract
        ) {
            Log.d("Practice", it!!.data.toString())
        }

        chooseDocumentBtn.setOnClickListener {
            launcher.launch(intent)
        }

        return view
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

}