
// implemented counter app where learned concepts of dataclass,
package com.example.app_demo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.app_demo.databinding.ActivityMainBinding

import androidx.activity.viewModels







class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: counterviewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeState()

        binding.btnIncrement.setOnClickListener {
            viewModel.handleAction(UserAction.Increment)
        }

        binding.btnDecrement.setOnClickListener {
            viewModel.handleAction(UserAction.Decrement)
        }

        binding.hangit.setOnClickListener {
            viewModel.handleAction(UserAction.Hang)
        }
    }

    private fun observeState() {
        viewModel.counter.observe(this) { state ->
            binding.counterView.text = state.value.toString()
        }
    }
}



