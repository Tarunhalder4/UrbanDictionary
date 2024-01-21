package com.example.urbandictionary

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {
    @Provides
    @Singleton
    fun getFireBaseInstance(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun getFirebaseDataBaseInstance(): FirebaseDatabase {
        return Firebase.database
    }

    @Provides
    @Singleton
    fun getBaseUrl(): String =
        "https://mashape-community-urban-dictionary.p.rapidapi.com"

    @Provides
    @Singleton
    fun getRetrofitInstance(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun getDefineService(retrofit: Retrofit): Service {
        return retrofit.create(Service::class.java)
    }

    @Provides
    @Singleton
    fun getSharePreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Util.MY_PREFERENCE, Context.MODE_PRIVATE)
    }


}