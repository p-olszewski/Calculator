package com.example.calculator

import android.os.Bundle

class AdvancedCalculator : Calculator() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.advanced_calculator)
        supportActionBar?.title = "Advanced Calculator"
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //back arrow
        displayPrepare()
    }
}

