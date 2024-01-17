package com.example.urbandictionary

import com.example.urbandictionary.data.ResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Service {


    @GET("/define")
    @Headers(
//        "X-RapidAPI-Key: d8edeb566dmshc5ec050ed8cf301p137f83jsn26421ea8eff0",
//        "X-RapidAPI-Host: mashape-community-urban-dictionary.p.rapidapi.com"
        "${Util.X_RapidAPI_KEY}:${Util.X_RapidAPI_KEY_VALUE}",
        "${Util.X_RapidAPI_HOST}:${Util.X_RapidAPI_KEY_HOST_VALUE}"
    )
    fun getDefine(@Query("term") term:String):Call<ResponseData>



}