package com.example.urbandictionary

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

object Extension {

    fun Context.showMessage(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
        Log.d("tarun", "showToast: $message")
    }

}