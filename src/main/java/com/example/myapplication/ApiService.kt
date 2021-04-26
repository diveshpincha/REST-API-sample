package com.example.myapplication

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

val sourceUrl="https://official-joke-api.appspot.com/"


interface JokeApiGetter{
    @GET("random_joke")
    fun getJoke():Deferred<JOKES>
}


private val moshi=Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit=Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(sourceUrl)
        .build()


object JokesApi{
    val retrofitGetter : JokeApiGetter by lazy {
        retrofit.create(JokeApiGetter::class.java)
    }
}