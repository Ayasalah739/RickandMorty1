package com.example.rickandmorty1.network

import com.example.rickandmorty1.model.Character
import com.example.rickandmorty1.model.CharacterApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("character")
    suspend fun getCharacters(): CharacterApiResponse

    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") id: Int): Character

    @GET("character/{id}")
    fun getCharacterDetailsCall(@Path("id") id: Int): Call<Character>
}

