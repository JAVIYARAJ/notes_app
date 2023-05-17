    package com.example.notesapp.adapter

    import android.content.Context
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView
    import com.example.notesapp.R
    import com.example.notesapp.models.NoteCategoryList

    class NoteCategoryAdapter(var noteCategoryList: ArrayList<NoteCategoryList>, var context: Context) :
        RecyclerView.Adapter<NoteCategoryAdapter.MyNoteViewModel>() {
        class MyNoteViewModel(view: View) : RecyclerView.ViewHolder(view) {

            var noteCategoryTitle: TextView = view.findViewById<TextView>(R.id.noteCategoryName)
            var noteCategoryNoteCount: TextView = view.findViewById<TextView>(R.id.noteCategoryTotal)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNoteViewModel {
            var view =
                LayoutInflater.from(context).inflate(R.layout.note_category_layout, parent, false)
            return MyNoteViewModel(view)
        }

        override fun onBindViewHolder(holder: MyNoteViewModel, position: Int) {
            var item = noteCategoryList[position]

            holder.noteCategoryTitle.text=item.name;
            holder.noteCategoryNoteCount.text=item.notesCount.toString();
        }

        override fun getItemCount(): Int {
            return noteCategoryList.size
        }
    }