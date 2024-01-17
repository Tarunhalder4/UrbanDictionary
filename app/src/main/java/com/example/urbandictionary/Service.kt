package com.example.urbandictionary

import retrofit2.Call
import retrofit2.http.GET

interface Service {


    @GET("/define")
    fun getDefine():Call<ResponseData>



}