package com.example.myapplication2

import android.app.Activity
import android.os.Bundle

class HelloWorld : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_hello_world)
    }
}