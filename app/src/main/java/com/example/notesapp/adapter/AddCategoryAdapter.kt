package com.example.notesapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.models.Notes

class AddCategoryAdapter(var categoryList: ArrayList<String>, var context: Context) :
    RecyclerView.Adapter<AddCategoryAdapter.MyNoteViewModel>() {
    class MyNoteViewModel(view: View) : RecyclerView.ViewHolder(view) {

        var categoryTitle: TextView = view.findViewById<TextView>(R.id.categoryTitle)
        var removeTaskCategory: ImageView = view.findViewById(R.id.removeTaskCategory);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNoteViewModel {
        val view =
            LayoutInflater.from(context).inflate(R.layout.custom_add_category_layout, parent, false)
        return MyNoteViewModel(view)
    }

    override fun onBindViewHolder(holder: MyNoteViewModel, position: Int) {
        val item = categoryList[position]

        holder.categoryTitle.text = item.toString()
        holder.removeTaskCategory.setOnClickListener {
            removeItem(position)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun addItem(text: String) {
        categoryList.add(text);
        notifyItemInserted(categoryList.size)
    }

    private fun removeItem(position: Int) {
        categoryList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, categoryList.size)
    }
}