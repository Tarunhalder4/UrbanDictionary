package com.example.urbandictionary

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
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
            signUp(binding)
        }

        signUpStatus()

    }


    private fun signUp(binding:ActivitySignupBinding){
        if(!binding.name.text.toString().isEmpty()){
            if(!binding.age.text.toString().isEmpty()){
                if(!binding.work.text.toString().isEmpty()){
                    if(!binding.email.text.toString().isEmpty()){
                        if(!binding.password.text.toString().isEmpty()){
                            if(!binding.conformPassword.text.toString().isEmpty()) {
                                if (binding.password.text.toString() == binding.conformPassword.text.toString()) {
                                    viewModel.resistorUser(binding.email.text.toString(),binding.password.text.toString())
                                } else {
                                    Util.showMessage(this@SignupActivity,this.getString(R.string.password_and_conform_password_not_match))
                                }
                            }else{
                                Util.showMessage(this@SignupActivity,this.getString(R.string.please_entry_conform_password))
                            }
                        }else{
                            Util.showMessage(this@SignupActivity,this.getString(R.string.please_entry_password))
                        }
                        }else{
                            Util.showMessage(this@SignupActivity,this.getString(R.string.please_entry_email))
                        }
                }else{
                    Util.showMessage(this@SignupActivity,this.getString(R.string.please_entry_work))
                }
            }else{
                Util.showMessage(this@SignupActivity,this.getString(R.string.please_entry_age))
            }
        }else{
            Util.showMessage(this@SignupActivity,this.getString(R.string.please_entry_name))
        }

    }

    private fun signUpStatus(){
        viewModel.resistorStatus.observe(this) {
            when(it.status){
                Result.LOADING ->{
                    binding.progress.visibility = View.VISIBLE
                }
                Result.SUCCESS ->{
                    binding.progress.visibility = View.GONE
                    Util.showMessage(this,this.getString(R.string.resistor_successfully))
                }
                else ->{
                    binding.progress.visibility = View.GONE
                    Util.showMessage(this,this.getString(R.string.resistor_failed)+" "+it.errorP )
                }
            }
        }

    }




}