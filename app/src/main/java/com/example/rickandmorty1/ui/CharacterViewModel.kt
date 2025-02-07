package com.example.rickandmorty1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty1.data.CharacterRepository
import com.example.rickandmorty1.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterViewModel(private val repository: CharacterRepository) : ViewModel() {

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> get() = _characters

    private val _characterData = MutableLiveData<Character?>()
    val characterData: LiveData<Character?> get() = _characterData

    fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val characterList = repository.getCharacters()
                _characters.postValue(characterList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchCharacterById(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val character = repository.getCharacterById(characterId)
                character?.let {
                    _characterData.postValue(it)
                } ?: run {
                    _characterData.postValue(null)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _characterData.postValue(null)
            }
        }
    }
}


