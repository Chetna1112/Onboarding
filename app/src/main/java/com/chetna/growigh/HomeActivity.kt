package com.chetna.growigh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chetna.growigh.databinding.ActivityHomeBinding
import com.chetna.growigh.databinding.ActivityHomeBinding.inflate

class HomeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.feed.setOnClickListener {
            startActivity(Intent(this,FeedActivity::class.java))
        }
        binding.upload.setOnClickListener {
            startActivity(Intent(this,UploadActivity::class.java))
        }

    }
}