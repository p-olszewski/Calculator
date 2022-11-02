package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "BasicCalculator"

class BasicCalculator : AppCompatActivity() {
    lateinit var calc_display: TextView
    var previousValue: String = "0"
    lateinit var operation: String
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basic_calculator)

        calc_display = findViewById<TextView>(R.id.tvResult)
        calc_display.text = "0"
        previousValue = calc_display.text.toString()
        setButtonListeners()
        Log.d(TAG, "First call: ${calc_display.text}")
    }

    fun setButtonListeners() {
        findViewById<Button>(R.id.button_0).setOnClickListener { numericButtonHandler(it) }
        findViewById<Button>(R.id.button_1).setOnClickListener { numericButtonHandler(it) }
        findViewById<Button>(R.id.button_2).setOnClickListener { numericButtonHandler(it) }
        findViewById<Button>(R.id.button_3).setOnClickListener { numericButtonHandler(it) }
        findViewById<Button>(R.id.button_4).setOnClickListener { numericButtonHandler(it) }
        findViewById<Button>(R.id.button_5).setOnClickListener { numericButtonHandler(it) }
        findViewById<Button>(R.id.button_6).setOnClickListener { numericButtonHandler(it) }
        findViewById<Button>(R.id.button_7).setOnClickListener { numericButtonHandler(it) }
        findViewById<Button>(R.id.button_8).setOnClickListener { numericButtonHandler(it) }
        findViewById<Button>(R.id.button_9).setOnClickListener { numericButtonHandler(it) }
    }

    private fun numericButtonHandler(view: View?) {
        if (view != null) {
            Log.d(TAG, "Event handler call: ${calc_display.text}")
            if (calc_display.text.toString() == "0") {
                calc_display.text = (view as Button).text
            } else {
                calc_display.append((view as Button).text)
            }
            lastNumeric = true;
        }
    }

    fun changeSignBtn(view: View?) {}

    fun divideBtn(view: View?) {}

    fun subtractBtn(view: View?) {}

    fun multiplyBtn(view: View?) {}

    fun resultBtn(view: View?) {}

    fun addBtn(view: View?) {
        previousValue = calc_display.text.toString()
        operation = "+"
    }

    fun dotBtn(view: View) {}

    fun allClearBtn(view: View?) {
        Log.d(TAG, "Event handler call: ${calc_display.text}")
        calc_display.text = "0"
        lastNumeric = false
        lastDot = false
    }
    fun bkspBtn(view: View?) {
        Log.d(TAG, "Event handler call: ${calc_display.text}")
        val stringLength = calc_display.text.length
        if (stringLength <= 1) {
            calc_display.text = "0"
        } else {
            calc_display.text = calc_display.text.substring(0, stringLength - 1)
        }
    }
}
