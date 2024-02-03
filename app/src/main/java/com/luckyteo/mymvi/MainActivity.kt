package com.luckyteo.mymvi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.luckyteo.mymvi.databinding.ActivityMainBinding
import com.luckyteo.mymvi.ui.SecondActivity
import com.luckyteo.mymvi.ui.main.MainContract
import com.luckyteo.mymvi.ui.main.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()
        binding.generateNumber.setOnClickListener {
            viewModel.setEvent(MainContract.Event.OnRandomNumberClicked)
        }
        binding.showToast.setOnClickListener {
            viewModel.setEvent(MainContract.Event.OnShowToastClicked)
        }
        binding.secondActivity.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    /**
     * Initialize Observers
     */
    private fun initObservers() {

        // process ViewState
        // update data to UI
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it.randomNumberState) {
                        is MainContract.RandomNumberState.Idle -> {
                            binding.progressBar.isVisible = false
                        }

                        is MainContract.RandomNumberState.Loading -> {
                            binding.progressBar.isVisible = true
                        }

                        is MainContract.RandomNumberState.Success -> {
                            binding.progressBar.isVisible = false
                            binding.number.text = it.randomNumberState.number.toString()
                        }
                    }
                }
            }
        }

        // process ViewEffect
        // update effect move screen, play animation, show popup, ...
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect {
                    when (it) {
                        is MainContract.Effect.ShowToast -> {
                            binding.progressBar.isVisible = false
                            showToast("Error, number is even")
                        }
                    }
                }
            }
        }
    }


    /**
     * Show simple toast message
     */
    private fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }
}