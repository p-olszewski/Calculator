package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        val calcBtnBasicCalc = findViewById<Button>(R.id.menu_btn_basic_calc)
        calcBtnBasicCalc.setOnClickListener {
            val intent = Intent(this, BasicCalculator::class.java)
            startActivity(intent)
        }

        val calcBtnAdvancedCalc = findViewById<Button>(R.id.menu_btn_advanced_calc)
        calcBtnAdvancedCalc.setOnClickListener {
            val intent = Intent(this, AdvancedCalculator::class.java)
            startActivity(intent)
        }

        val menuBtnAbout = findViewById<Button>(R.id.menu_btn_about)
        menuBtnAbout.setOnClickListener {
            val intent = Intent(this, AboutMe::class.java)
            startActivity(intent)
        }

        val menuBtnExit = findViewById<Button>(R.id.menu_btn_exit)
        menuBtnExit.setOnClickListener {
            finish()
        }
    }
}