package com.example.urbandictionary

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
        "https://mashape-community-urban-dictionary.p.rapidapi.com/define?term=wat"


    @Provides
    @Singleton
    fun getRetrofitInstance(baseUrl: String):Retrofit=
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()



}