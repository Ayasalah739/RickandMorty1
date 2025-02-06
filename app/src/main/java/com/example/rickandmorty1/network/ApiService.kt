package com.example.rickandmorty1.network

import com.example.rickandmorty1.model.CharacterApiResponse
import retrofit2.http.GET


interface ApiService {
    @GET("character")
    suspend fun getCharacters(): CharacterApiResponse
}
