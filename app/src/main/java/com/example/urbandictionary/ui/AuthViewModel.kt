package com.example.urbandictionary.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.urbandictionary.Repository
import com.example.urbandictionary.data.Status
import com.example.urbandictionary.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    application: Application,
    private val repository: Repository
) : AndroidViewModel(application) {

    val resistorStatus: MutableLiveData<Status<Boolean>> = repository.getResistorStatus()
    val loginStatus: MutableLiveData<Status<Boolean>> = repository.getLoginStatus()
    val saveUserInFirebaseStatus: MutableLiveData<Status<Boolean>> =
        repository.saveUserInFirebaseStatus()

    fun resistorUser(email: String, password: String) =
        viewModelScope.launch {
            repository.resistor(email, password)
        }

    fun saveUserInFirebase(user: User) {
        viewModelScope.launch {
            repository.saveUserInFireBase(user)
        }
    }

    fun loginUser(email: String, password: String) =
        viewModelScope.launch {
            repository.login(email, password)
        }

}

