package com.example.urbandictionary

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class Repository @Inject constructor(){

    @Inject
    lateinit var auth: FirebaseAuth
    private val resistorStatus: MutableLiveData<Status<Boolean>> = MutableLiveData<Status<Boolean>>()

    public fun getStatus():MutableLiveData<Status<Boolean>>{
        return resistorStatus
    }

    suspend fun resistor(email:String,password:String){
        resistorStatus.postValue(Status.Loading(Result.LOADING))
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {task->
            if(task.isSuccessful){
                resistorStatus.postValue(Status.Success(Result.SUCCESS,task.isSuccessful))
            }else{
                resistorStatus.postValue(Status.Success(Result.ERROR,task.isSuccessful))
                resistorStatus.postValue(Status.Error(Result.ERROR,task.exception.toString()))
            }
        }
    }


}