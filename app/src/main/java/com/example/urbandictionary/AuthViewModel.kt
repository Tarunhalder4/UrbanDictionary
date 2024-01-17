package com.example.urbandictionary

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(application: Application,private val repository: Repository):AndroidViewModel(application) {

    val resistorStatus:MutableLiveData<Status<Boolean>> = repository.getStatus()

    fun resistorUser(email:String,password:String)=
        viewModelScope.launch {
            repository.resistor(email, password)
        }

}

