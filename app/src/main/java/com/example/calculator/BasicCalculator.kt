package com.example.calculator

import android.os.Bundle

class BasicCalculator : Calculator() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basic_calculator)
        supportActionBar?.title = "Basic Calculator"
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //back arrow
        displayPrepare()
    }
}

