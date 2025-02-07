package com.example.rickandmorty1.data

import com.example.rickandmorty1.model.Character
import com.example.rickandmorty1.network.RetrofitClient

class CharacterRepository {

    private val apiService = RetrofitClient.apiService

    suspend fun getCharacters(): List<Character> {
        val response = apiService.getCharacters()
        return response.results
    }

    suspend fun getCharacterById(id: Int): Character? {
        return try {
            apiService.getCharacterDetails(id)
        } catch (e: Exception) {
            null
        }
    }
}





