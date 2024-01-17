package com.example.urbandictionary

import android.content.Context
import android.util.Log
import android.widget.Toast

object Util {

    fun showMessage(context: Context,message:String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
        Log.d("tarun", "showToast: $message")
    }

}