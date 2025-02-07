package com.example.rickandmorty1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty1.data.CharacterRepository
import com.example.rickandmorty1.model.Character
import kotlinx.coroutines.launch

class CharacterDetailViewModel(private val repository: CharacterRepository) : ViewModel() {

    private val _character = MutableLiveData<Character?>()
    val character: LiveData<Character?> get() = _character

    fun fetchCharacter(characterId: Int) {
        viewModelScope.launch {
            try {
                val character = repository.getCharacterById(characterId)
                _character.postValue(character)
            } catch (e: Exception) {
                e.printStackTrace()
                _character.postValue(null)
            }
        }
    }
}

