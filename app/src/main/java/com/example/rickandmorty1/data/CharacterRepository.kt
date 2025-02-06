package com.example.rickandmorty1.data

import com.example.rickandmorty1.model.Character
import com.example.rickandmorty1.network.RetrofitClient

class CharacterRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun fetchCharacters(): List<Character> {
        val response = apiService.getCharacters()
        return response.results
    }
}

