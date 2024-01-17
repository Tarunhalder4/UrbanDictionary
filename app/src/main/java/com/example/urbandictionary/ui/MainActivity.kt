 package com.example.urbandictionary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urbandictionary.DefineAdapter
import com.example.urbandictionary.R
import com.example.urbandictionary.data.Result
import com.example.urbandictionary.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

 @AndroidEntryPoint
class MainActivity: AppCompatActivity() {

     @Inject
     lateinit var baseUrl:String
     private lateinit var viewModel: MainViewModel
     private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recycleView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        defineStatus()
        viewModel.getDefineData("wat")


    }

     private fun defineStatus(){
         viewModel.defineStatus.observe(this){it->
             when(it.status){
                 Result.LOADING ->{
                     Log.d("tarun", "loading")
                     binding.progress.visibility = View.VISIBLE
                 }
                 Result.SUCCESS ->{
                     Log.d("tarun", "success")
                     binding.progress.visibility = View.GONE
                     val adapter = DefineAdapter(it.data!!.list)
                     binding.recycleView.adapter = adapter
                 }
                 else ->{
                     Log.d("tarun", "error: ${it.errorP}")
                     binding.progress.visibility = View.GONE
                 }

             }

         }
     }
}