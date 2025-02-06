package com.example.rickandmorty1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty1.databinding.ActivityMainBinding
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
                if (binding.recyclerView.adapter == null) {
                    binding.recyclerView.adapter = CharacterAdapter(it)
                } else {
                    (binding.recyclerView.adapter as CharacterAdapter).updateData(it)
                }
            }
        }
    }
}













