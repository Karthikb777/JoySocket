package com.karthik.socketcontroller

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private val EXTRA_MESSAGE = "com.karthik.socketontroller.extra_message"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openController(view: View) {
        val ipInput = findViewById<EditText>(R.id.ipInput)

        val controllerIntent = Intent(this, ControllerNew::class.java).apply {
            putExtra(EXTRA_MESSAGE, ipInput.text.toString())
        }
        startActivity(controllerIntent)
    }
}