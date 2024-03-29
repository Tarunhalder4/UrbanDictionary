package com.example.urbandictionary

import com.example.urbandictionary.data.ResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Service {
    @GET("/define")
    @Headers(
        "${Util.X_RapidAPI_KEY}:${Util.X_RapidAPI_KEY_VALUE}",
        "${Util.X_RapidAPI_HOST}:${Util.X_RapidAPI_KEY_HOST_VALUE}"
    )
    fun getDefine(@Query("term") term: String): Call<ResponseData>

}
