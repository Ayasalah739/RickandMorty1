package com.example.rickandmorty1

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty1.data.CharacterRepository
import com.example.rickandmorty1.databinding.ActivityMainBinding
import com.example.rickandmorty1.ui.CharacterDetailActivity
import com.example.rickandmorty1.ui.CharacterViewModel
import com.example.rickandmorty1.ui.CharacterViewModelFactory
import com.example.rickandmorty1.ui.adapter.CharacterAdapter

class MainActivity : AppCompatActivity() {

    private val characterViewModel: CharacterViewModel by viewModels {
        CharacterViewModelFactory(CharacterRepository())
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        characterViewModel.characters.observe(this) { characters ->
            characters?.let {
                val adapter = CharacterAdapter(it) { character ->
                    navigateToCharacterDetail(character.id)
                }
                binding.recyclerView.adapter = adapter
            }
        }

        characterViewModel.fetchCharacters()
    }

    private fun navigateToCharacterDetail(characterId: Int) {
        val intent = Intent(this, CharacterDetailActivity::class.java).apply {
            putExtra("CHARACTER_ID", characterId)
        }
        startActivity(intent)
    }
}















