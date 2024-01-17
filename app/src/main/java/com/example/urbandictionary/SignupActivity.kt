package com.example.urbandictionary

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.urbandictionary.Extension.showMessage
import com.example.urbandictionary.databinding.ActivitySignupBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: AuthViewModel
    @Inject
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_signup)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        binding.submit.setOnClickListener {
            signUp()
        }

        signUpStatus()

    }


    private fun signUp(){
        if(!binding.name.text.toString().isEmpty()){
            if(!binding.age.text.toString().isEmpty()){
                if(!binding.work.text.toString().isEmpty()){
                    if(!binding.email.text.toString().isEmpty()){
                        if(!binding.password.text.toString().isEmpty()){
                            if(!binding.conformPassword.text.toString().isEmpty()) {
                                if (binding.password.text.toString() == binding.conformPassword.text.toString()) {
                                    viewModel.resistorUser(binding.email.text.toString(),binding.password.text.toString())
                                } else {
                                    this.showMessage(getString(R.string.password_and_conform_password_not_match))
                                }
                            }else{
                                this.showMessage(getString(R.string.please_entry_conform_password))
                            }
                        }else{
                            this.showMessage(getString(R.string.please_entry_password))
                        }
                        }else{
                        this.showMessage(getString(R.string.please_entry_email))
                        }
                }else{
                    this.showMessage(getString(R.string.please_entry_work))
                }
            }else{
                this.showMessage(getString(R.string.please_entry_age))
            }
        }else{
            this.showMessage(getString(R.string.please_entry_name))
        }

    }

    private fun signUpStatus(){
        viewModel.resistorStatus.observe(this) {
            when(it.status){
                Result.LOADING ->{
                    binding.progress.visibility = View.VISIBLE
                }
                Result.SUCCESS ->{
                    clearAllField()
                    binding.progress.visibility = View.GONE
                    this.showMessage(this.getString(R.string.resistor_successfully))
                    startActivity(Intent(this,MainActivity::class.java))
                }
                else ->{
                    binding.progress.visibility = View.GONE
                    this.showMessage(this.getString(R.string.resistor_failed)+" "+it.errorP )
                }
            }
        }

    }

    private fun clearAllField(){
        binding.name.text?.clear()
        binding.age.text?.clear()
        binding.work.text?.clear()
        binding.email.text?.clear()
        binding.password.text?.clear()
        binding.conformPassword.text?.clear()
    }




}