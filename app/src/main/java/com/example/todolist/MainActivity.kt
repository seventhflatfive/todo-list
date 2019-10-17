package com.example.todolist

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.R.layout
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            var addIntent = Intent(this, AddToDoActivity::class.java)
            startActivity(addIntent)
        }
    }

    override fun onResume() {
        super.onResume()

        val realm = Realm.getDefaultInstance()
        val query = realm.where(ToDoItem::class.java)
        val results : RealmResults<ToDoItem> = query.findAll()

        val toDoAdapter = ToDoAdapter(this, R.layout.todo_list_item, results)
        val listView = findViewById<ListView>(R.id.toDoItemListView)
        listView.adapter = toDoAdapter
        toDoAdapter.notifyDataSetChanged()

        // stupid textview hiding
        if (results.isEmpty()) {
            textNoToDos.visibility = View.VISIBLE
        } else {
            textNoToDos.visibility = View.GONE
        }
        toDoAdapter.notifyDataSetChanged()

        listView.setOnItemClickListener { _, _, position, _ ->

            Realm.getDefaultInstance()
            val selectedToDo = results[position]
            val finishIntent = Intent(this, FinishToDoActivity::class.java)
            finishIntent.putExtra("toDoItem", selectedToDo?.getId())
            startActivity(finishIntent)
            toDoAdapter.notifyDataSetChanged()

        }


    }

}

class ToDoAdapter(context: Context, resource: Int, objects: MutableList<ToDoItem>) :
    ArrayAdapter<ToDoItem>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val toDoView =
            inflater.inflate(
                layout.todo_list_item,
                parent,
                false
            ) as TextView

        val toDoItem = getItem(position)

        toDoView.text = toDoItem?.name

        if (toDoItem?.important!!) {
            toDoView.typeface = Typeface.DEFAULT_BOLD
        }

        return toDoView
    }

}

