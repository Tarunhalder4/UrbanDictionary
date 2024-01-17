package com.example.urbandictionary.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.urbandictionary.Repository
import com.example.urbandictionary.data.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(application: Application,private val repository: Repository):AndroidViewModel(application) {

    val resistorStatus:MutableLiveData<Status<Boolean>> = repository.getResistorStatus()
    val loginStatus:MutableLiveData<Status<Boolean>> = repository.getLoginStatus()

    fun resistorUser(email:String,password:String)=
        viewModelScope.launch {
            repository.resistor(email, password)
        }

    fun loginUser(email:String,password:String)=
        viewModelScope.launch {
            repository.login(email, password)
        }

}

