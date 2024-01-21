package com.example.urbandictionary

import androidx.lifecycle.MutableLiveData
import com.example.urbandictionary.data.ResponseData
import com.example.urbandictionary.data.Result
import com.example.urbandictionary.data.Status
import com.example.urbandictionary.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(val service: Service) {

    @Inject
    lateinit var auth: FirebaseAuth

    @Inject
    lateinit var firebaseDatabase: FirebaseDatabase
    private val resistorStatus: MutableLiveData<Status<Boolean>> =
        MutableLiveData<Status<Boolean>>()
    private val loginStatus: MutableLiveData<Status<Boolean>> = MutableLiveData<Status<Boolean>>()
    private val defineStatus: MutableLiveData<Status<ResponseData?>> =
        MutableLiveData<Status<ResponseData?>>()
    private val saveUserInFireStatus: MutableLiveData<Status<Boolean>> =
        MutableLiveData<Status<Boolean>>()

    fun getResistorStatus() = resistorStatus
    fun getLoginStatus() = loginStatus
    fun saveUserInFirebaseStatus() = saveUserInFireStatus
    fun getDefineStatus() = defineStatus

    suspend fun resistor(email: String, password: String) {
        resistorStatus.postValue(Status.Loading(Result.LOADING))
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                resistorStatus.postValue(Status.Success(Result.SUCCESS, task.isSuccessful))
            } else {
                resistorStatus.postValue(Status.Error(Result.ERROR, task.exception.toString()))
            }
        }
    }

    suspend fun login(email: String, password: String) {
        loginStatus.postValue(Status.Loading(Result.LOADING))
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                loginStatus.postValue(Status.Success(Result.SUCCESS, task.isSuccessful))
            } else {
                loginStatus.postValue(Status.Error(Result.ERROR, task.exception.toString()))
            }
        }
    }

    suspend fun saveUserInFireBase(user: User) {
        saveUserInFireStatus.postValue(Status.Loading(Result.LOADING))
        firebaseDatabase.getReference("User").child(auth.currentUser!!.uid).setValue(user)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    saveUserInFireStatus.postValue(
                        Status.Success(
                            Result.SUCCESS,
                            task.isSuccessful
                        )
                    )
                } else {
                    saveUserInFireStatus.postValue(
                        Status.Error(
                            Result.ERROR,
                            task.exception.toString()
                        )
                    )
                }
            }
    }

    suspend fun getDefineData(term: String) {
        defineStatus.postValue(Status.Loading(Result.LOADING))
        service.getDefine(term).enqueue(object : Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                defineStatus.postValue(Status.Success(Result.SUCCESS, response.body()))
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                defineStatus.postValue(Status.Error(Result.ERROR, t.message.toString()))
            }
        })
    }


}