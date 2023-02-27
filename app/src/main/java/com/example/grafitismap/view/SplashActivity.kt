package com.example.grafitismap.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.grafitismap.databinding.ActivitySplashBinding
import com.example.grafitismap.viewmodel.SplashViewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel : SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(viewModel.loggedIn()){
            //go to map
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            //go to login
            val intent = Intent(this, UserRegisterActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}