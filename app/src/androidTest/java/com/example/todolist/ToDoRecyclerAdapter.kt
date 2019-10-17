package com.example.todolist.adapters

import com.example.todolist.R
import com.example.todolist.models.ToDos
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.content_main.view.*
import kotlinx.android.synthetic.main.movie_list_row.view.*

class ToDoRecyclerAdapter(private val todoList: List<ToDos>) : RecyclerView.Adapter<ToDoRecyclerAdapter.MyViewHolder>() {

    inner class  MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var text: TextView = view.findViewById<TextView>()
    }

}