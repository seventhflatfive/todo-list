package com.example.todolist

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm

class FinishToDoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_to_do)

        title = "Delete ToDo"

        val toDoItemId = intent.getStringExtra("toDoItem")

        val realm = Realm.getDefaultInstance()

        val toDoItem = realm.where(ToDoItem::class.java).equalTo("id", toDoItemId).findFirst()

        val textView = findViewById<TextView>(R.id.toDoNameTextView)
        textView.text = toDoItem?.name

        // when btn is pressed do commits
        val completeButton = findViewById<Button>(R.id.completeButton)

        completeButton.setOnClickListener {

            realm.beginTransaction()
            toDoItem?.deleteFromRealm()
            realm.commitTransaction()

            finish()
        }


    }
}

