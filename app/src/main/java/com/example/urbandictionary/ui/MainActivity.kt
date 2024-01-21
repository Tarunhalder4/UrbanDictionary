package com.example.urbandictionary.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urbandictionary.DefineAdapter
import com.example.urbandictionary.R
import com.example.urbandictionary.data.Result
import com.example.urbandictionary.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var baseUrl: String

    @Inject
    lateinit var auth: FirebaseAuth

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.toolbar)
        binding.recycleView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        defineStatus()
        viewModel.getDefineData("wat")


    }

    private fun defineStatus() {
        viewModel.defineStatus.observe(this) { it ->
            when (it.status) {
                Result.LOADING -> {
                    Log.d("tarun", "loading")
                    binding.progress.visibility = View.VISIBLE
                }

                Result.SUCCESS -> {
                    Log.d("tarun", "success")
                    binding.progress.visibility = View.GONE
                    val adapter = DefineAdapter(it.data!!.list)
                    binding.recycleView.adapter = adapter
                }

                else -> {
                    Log.d("tarun", "error: ${it.errorP}")
                    binding.progress.visibility = View.GONE
                }

            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.log_out) {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}