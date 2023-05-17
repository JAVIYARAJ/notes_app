package com.example.notesapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.adapter.NoteCategoryAdapter
import com.example.notesapp.R
import com.example.notesapp.models.NoteCategoryList

class CategoryFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_category, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.noteCategoryRecyclerView);

        val data = ArrayList<NoteCategoryList>();
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

        val noteAdapter= NoteCategoryAdapter(noteCategoryList = data, context = requireContext().applicationContext)
        recyclerView.layoutManager= GridLayoutManager(requireContext().applicationContext,2,)
        recyclerView.adapter=noteAdapter;
        noteAdapter.notifyDataSetChanged();
        return view;
    }

}