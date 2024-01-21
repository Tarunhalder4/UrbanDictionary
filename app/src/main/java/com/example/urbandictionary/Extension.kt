package com.example.urbandictionary

import android.content.Context
import android.util.Log
import android.widget.Toast

object Extension {

    fun Context.showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.d("tarun", "showToast: $message")
    }

}