package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.joke.observe(this, Observer {
            binding.textView.text= it.setup + "\n" + it.punchline
        })

        binding.button.setOnClickListener{
            viewModel.getJoke()
        }

        setContentView(binding.root)
    }
}