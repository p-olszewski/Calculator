package com.example.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AboutMe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_me)
        supportActionBar?.title = "About me"
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //back arrow
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}