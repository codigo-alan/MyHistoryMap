package com.example.grafitismap.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.grafitismap.R
import com.example.grafitismap.databinding.ActivityUserRegisterBinding

class UserRegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}