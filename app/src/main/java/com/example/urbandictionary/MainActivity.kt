 package com.example.urbandictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

 @AndroidEntryPoint
class MainActivity: AppCompatActivity() {

     @Inject
     lateinit var baseUrl:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Log.d("tarun", "onCreate: $baseUrl")

    }
}