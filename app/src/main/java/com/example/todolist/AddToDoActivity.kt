package com.example.todolist

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import org.jetbrains.anko.toast

class AddToDoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_do)

        title = "New ToDo"

        val button = findViewById<Button>(R.id.addButton)
        button.setOnClickListener {

            val editText = findViewById<EditText>(R.id.toDoEditText)
            val checkBox = findViewById<CheckBox>(R.id.importantCheckBox)

            val todo = ToDoItem()
            todo.name = editText.text.toString().trimStart().trimEnd()
            todo.important = checkBox.isChecked
            if (!todo.name.isBlank()) {

                val realm = Realm.getDefaultInstance()
                realm.beginTransaction()
                realm.copyToRealm(todo)
                realm.commitTransaction()

                finish()
            } else {
                toast("Can't add empty ToDo!")
            }
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
