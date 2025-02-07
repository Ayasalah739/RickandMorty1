package com.example.rickandmorty1.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmorty1.R
import com.example.rickandmorty1.data.CharacterRepository
import com.example.rickandmorty1.databinding.ActivityCharacterDetailBinding
import com.example.rickandmorty1.model.Character
import com.squareup.picasso.Picasso

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailBinding

    private val characterDetailViewModel: CharacterDetailViewModel by viewModels {
        CharacterDetailViewModelFactory(CharacterRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val characterId = intent.getIntExtra("CHARACTER_ID", -1)
        if (characterId != -1) {
            characterDetailViewModel.fetchCharacter(characterId)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        characterDetailViewModel.character.observe(this) { character ->
            character?.let {
                updateUI(it)
            } ?: run {
                showErrorUI()
            }
        }
    }

    private fun updateUI(character: Character) {
        binding.characterName.text = character.name
        binding.characterSpecies.text = character.species
        binding.characterStatus.text = character.status
        Picasso.get().load(character.image).into(binding.characterImage)
    }

    private fun showErrorUI() {
        binding.characterName.text = getString(R.string.character_not_found)
        binding.characterSpecies.text = ""
        binding.characterStatus.text = ""
        binding.characterImage.setImageResource(R.drawable.placeholder_image)
    }
}

