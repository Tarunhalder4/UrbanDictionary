package com.example.urbandictionary.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.urbandictionary.Repository
import com.example.urbandictionary.data.ResponseData
import com.example.urbandictionary.data.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(application: Application, private val repository: Repository):AndroidViewModel(application) {

    val defineStatus:MutableLiveData<Status<ResponseData?>> = repository.getDefineStatus()

    fun getDefineData(term:String){
        viewModelScope.launch {
            repository.getDefineData(term)
        }
    }
}