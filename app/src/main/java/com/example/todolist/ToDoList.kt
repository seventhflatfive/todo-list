package com.example.todolist

import android.app.Application
import io.realm.Realm

open class ToDoList : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }


}