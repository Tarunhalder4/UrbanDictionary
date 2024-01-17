package com.example.urbandictionary

sealed class Status<T>(val status: Result,val data:T?= null, val errorP: String? = null){
    class Success<T>(status:Result,data:T):Status<T>(status,data)
    class Loading<T>(status:Result):Status<T>(status)
    class Error<T>(status:Result,error:String):Status<T>(status, errorP = error)
}

enum class Result{
    LOADING,
    SUCCESS,
    ERROR
}