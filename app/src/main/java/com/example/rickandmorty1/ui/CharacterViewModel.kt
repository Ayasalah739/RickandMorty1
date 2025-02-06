package com.example.rickandmorty1.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.rickandmorty1.data.CharacterRepository
import kotlinx.coroutines.Dispatchers

class CharacterViewModel : ViewModel() {

    // Initialize the repository
    private val repository = CharacterRepository()

    // LiveData to hold the characters
    val characters = liveData(Dispatchers.IO) {
        // Fetch characters from the repository
        val data = repository.fetchCharacters()
        // Emit the data to the LiveData observer
        emit(data)
    }
}
