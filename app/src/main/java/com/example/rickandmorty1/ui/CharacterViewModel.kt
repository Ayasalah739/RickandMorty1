package com.example.rickandmorty1.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.rickandmorty1.data.CharacterRepository
import kotlinx.coroutines.Dispatchers

class CharacterViewModel : ViewModel() {

    private val repository = CharacterRepository()

    val characters = liveData(Dispatchers.IO) {
        val data = repository.fetchCharacters()
        emit(data)
    }
}
