package com.example.water_reminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.water_reminder.databinding.ActivityMainBinding
import com.example.water_reminder.utils.TextUtils

class MainActivity : ComponentActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.greetings.text = TextUtils.getGreetings();
    }
}