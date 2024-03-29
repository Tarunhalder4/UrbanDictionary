package com.example.urbandictionary.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.urbandictionary.Extension.showMessage
import com.example.urbandictionary.R
import com.example.urbandictionary.data.Result
import com.example.urbandictionary.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        if (auth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        buttonClick()
        loginStatus()

    }

    private fun buttonClick() {
        binding.submit.setOnClickListener {
            login()
        }

        binding.goToSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }

    }

    private fun login() {
        if (!binding.email.text.toString().isEmpty()) {
            if (!binding.password.text.toString().isEmpty()) {
                viewModel.loginUser(binding.email.text.toString(), binding.password.text.toString())
            } else {
                this.showMessage(getString(R.string.please_entry_password))
            }
        } else {

            this.showMessage(getString(R.string.please_entry_email))
        }
    }

    private fun loginStatus() {
        viewModel.loginStatus.observe(this) {
            when (it.status) {
                Result.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                }

                Result.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    this.showMessage(getString(R.string.resistor_successfully))
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

                else -> {
                    binding.progress.visibility = View.GONE
                    this.showMessage(getString(R.string.resistor_failed) + " " + it.errorP)
                }

            }

        }

    }


}