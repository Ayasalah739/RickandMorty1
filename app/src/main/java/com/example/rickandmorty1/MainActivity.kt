package com.example.rickandmorty1

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty1.databinding.ActivityMainBinding
import com.example.rickandmorty1.ui.CharacterDetailActivity
import com.example.rickandmorty1.ui.CharacterViewModel
import com.example.rickandmorty1.ui.adapter.CharacterAdapter

class MainActivity : AppCompatActivity() {

    private val characterViewModel: CharacterViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearLayoutManager

        characterViewModel.characters.observe(this) { characters ->
            characters?.let {
                binding.recyclerView.adapter = CharacterAdapter(it) { character ->
                    val intent = Intent(this, CharacterDetailActivity::class.java).apply {
                        putExtra("CHARACTER_ID", character.id)
                    }
                    startActivity(intent)
                }
            }
        }
    }
}














