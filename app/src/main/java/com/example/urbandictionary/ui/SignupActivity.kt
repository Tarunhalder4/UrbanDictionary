package com.example.urbandictionary.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.urbandictionary.Extension.showMessage
import com.example.urbandictionary.R
import com.example.urbandictionary.Util
import com.example.urbandictionary.data.Result
import com.example.urbandictionary.data.User
import com.example.urbandictionary.databinding.ActivitySignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        buttonClick()

        signUpStatus()
        saveUserInFirebaseStatus()

    }

    private fun buttonClick() {
        binding.submit.setOnClickListener {
            signUp()
        }

        binding.goToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    private fun signUp() {
        if (!binding.name.text.toString().isEmpty()) {
            if (!binding.age.text.toString().isEmpty()) {
                if (!binding.work.text.toString().isEmpty()) {
                    if (!binding.email.text.toString().isEmpty()) {
                        if (!binding.password.text.toString().isEmpty()) {
                            if (!binding.conformPassword.text.toString().isEmpty()) {
                                if (binding.password.text.toString() == binding.conformPassword.text.toString()) {
                                    showMessage(binding.email.text.toString())
                                    viewModel.resistorUser(
                                        binding.email.text.toString(),
                                        binding.password.text.toString()
                                    )
                                } else {
                                    showMessage(getString(R.string.password_and_conform_password_not_match))
                                }
                            } else {
                                showMessage(getString(R.string.please_entry_conform_password))
                            }
                        } else {
                            showMessage(getString(R.string.please_entry_password))
                        }
                    } else {
                        showMessage(getString(R.string.please_entry_email))
                    }
                } else {
                    showMessage(getString(R.string.please_entry_work))
                }
            } else {
                showMessage(getString(R.string.please_entry_age))
            }
        } else {
            showMessage(getString(R.string.please_entry_name))
        }

    }

    private fun signUpStatus() {
        viewModel.resistorStatus.observe(this) {
            when (it.status) {
                Result.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                }

                Result.SUCCESS -> {
                    val user = User(
                        binding.name.text.toString(),
                        binding.age.text.toString(),
                        binding.work.text.toString(),
                        binding.email.text.toString()
                    )
                    viewModel.saveUserInFirebase(user)

                    clearAllField()
                    binding.progress.visibility = View.GONE
                    showMessage(this.getString(R.string.resistor_successfully))
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

                else -> {
                    binding.progress.visibility = View.GONE
                    showMessage(this.getString(R.string.resistor_failed) + " " + it.errorP)
                }
            }
        }

    }

    private fun saveUserInFirebaseStatus() {
        viewModel.saveUserInFirebaseStatus.observe(this) {
            when (it.status) {
                Result.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                }

                Result.SUCCESS -> {
                    binding.progress.visibility = View.INVISIBLE
                    showMessage(getString(R.string.save_data_firebase))
                }

                Result.ERROR -> {
                    binding.progress.visibility = View.INVISIBLE
                    showMessage(it.errorP.toString())
                }
            }
        }
    }

    private fun clearAllField() {
        binding.name.text?.clear()
        binding.age.text?.clear()
        binding.work.text?.clear()
        binding.email.text?.clear()
        binding.password.text?.clear()
        binding.conformPassword.text?.clear()
    }


}