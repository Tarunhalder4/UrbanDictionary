package com.example.urbandictionary

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {


    @Provides
    @Singleton
    fun getFireBaseInstance():FirebaseAuth{
        return FirebaseAuth.getInstance()
    }


    @Provides
    @Singleton
    fun getBaseUrl():String =
        "https://mashape-community-urban-dictionary.p.rapidapi.com?term=wat"

   // https://mashape-community-urban-dictionary.p.rapidapi.com/define
   // "https://mashape-community-urban-dictionary.p.rapidapi.com/define?term=wat"


    @Provides
    @Singleton
    fun oKHttp():OkHttpClient{
        val client = OkHttpClient()

        val request: Request = Request.Builder()
            .url("https://mashape-community-urban-dictionary.p.rapidapi.com/define?term=wat")
            .get()
            .addHeader("X-RapidAPI-Key", "d8edeb566dmshc5ec050ed8cf301p137f83jsn26421ea8eff0")
            .addHeader("X-RapidAPI-Host", "mashape-community-urban-dictionary.p.rapidapi.com")
            .build()
        return client.apply { this.newCall(request) }

        //return  client.newCall(request)
    }
    @Provides
    @Singleton
    fun getRetrofitInstance(baseUrl: String, okHttpClient: OkHttpClient):Retrofit=
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()



}