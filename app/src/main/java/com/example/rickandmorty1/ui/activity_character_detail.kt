package com.example.rickandmorty1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmorty1.databinding.ActivityCharacterDetailBinding
import com.example.rickandmorty1.model.Character
import com.example.rickandmorty1.network.ApiService
import com.example.rickandmorty1.network.RetrofitInstance
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val characterId = intent.getIntExtra("CHARACTER_ID", -1)

        if (characterId != -1) {

            fetchCharacterDetails(characterId)
        }
    }

    private fun fetchCharacterDetails(characterId: Int) {
        val apiService = RetrofitInstance.create(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val character = apiService.getCharacterDetails(characterId)
                withContext(Dispatchers.Main) {
                    updateUI(character)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun updateUI(character: Character) {
        binding.characterName.text = character.name
        binding.characterSpecies.text = character.species
        binding.characterStatus.text = character.status
        Picasso.get().load(character.image).into(binding.characterImage)
    }
}

