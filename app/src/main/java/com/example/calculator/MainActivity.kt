package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var calc_display: TextView
    var previousValue: String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calc_display = findViewById<TextView>(R.id.tvResult)
        calc_display.text = ""
        previousValue = calc_display.text.toString()
        setButtonListeners()
        Log.d(TAG, "First call: ${calc_display.text}")
    }

    fun setButtonListeners() {
        findViewById<Button>(R.id.button_0).setOnClickListener { buttonEventHandler(it) }
        findViewById<Button>(R.id.button_1).setOnClickListener { buttonEventHandler(it) }
        findViewById<Button>(R.id.button_2).setOnClickListener { buttonEventHandler(it) }
        findViewById<Button>(R.id.button_3).setOnClickListener { buttonEventHandler(it) }
        findViewById<Button>(R.id.button_4).setOnClickListener { buttonEventHandler(it) }
        findViewById<Button>(R.id.button_5).setOnClickListener { buttonEventHandler(it) }
        findViewById<Button>(R.id.button_6).setOnClickListener { buttonEventHandler(it) }
        findViewById<Button>(R.id.button_7).setOnClickListener { buttonEventHandler(it) }
        findViewById<Button>(R.id.button_8).setOnClickListener { buttonEventHandler(it) }
        findViewById<Button>(R.id.button_9).setOnClickListener { buttonEventHandler(it) }
        findViewById<Button>(R.id.button_bksp).setOnClickListener { buttonEventHandler(it) }
        findViewById<Button>(R.id.button_ac).setOnClickListener { buttonEventHandler(it) }
        }

    private fun buttonEventHandler(it: View?) {
        if (it != null) {
            Log.d(TAG, "Event handler call: ${calc_display.text}")
            when (it.id) {
                R.id.button_0 -> {
                    if (calc_display.text != "0") {
                        calc_display.append(getString(R.string.button_0))
                    }
                }

                R.id.button_1 -> {
                    if (calc_display.text == "") {
                        calc_display.text = getString(R.string.button_1)
                    } else {
                        calc_display.append(getString(R.string.button_1))
                    }
                }

                R.id.button_2 -> {
                    if (calc_display.text == "0") {
                        calc_display.text = getString(R.string.button_2)
                    } else {
                        calc_display.append(getString(R.string.button_2))
                    }
                }

                R.id.button_3 -> {
                    if (calc_display.text == "0") {
                        calc_display.text = getString(R.string.button_3)
                    } else {
                        calc_display.append(getString(R.string.button_3))
                    }
                }

                R.id.button_4 -> {
                    if (calc_display.text == "0") {
                        calc_display.text = getString(R.string.button_4)
                    } else {
                        calc_display.append(getString(R.string.button_4))
                    }
                }

                R.id.button_5 -> {
                    if (calc_display.text == "0") {
                        calc_display.text = getString(R.string.button_5)
                    } else {
                        calc_display.append(getString(R.string.button_5))
                    }
                }

                R.id.button_6 -> {
                    if (calc_display.text == "0") {
                        calc_display.text = getString(R.string.button_6)
                    } else {
                        calc_display.append(getString(R.string.button_6))
                    }
                }

                R.id.button_7 -> {
                    if (calc_display.text == "0") {
                        calc_display.text = getString(R.string.button_7)
                    } else {
                        calc_display.append(getString(R.string.button_7))
                    }
                }

                R.id.button_8 -> {
                    if (calc_display.text == "0") {
                        calc_display.text = getString(R.string.button_8)
                    } else {
                        calc_display.append(getString(R.string.button_8))
                    }
                }

                R.id.button_9 -> {
                    if (calc_display.text == "0") {
                        calc_display.text = getString(R.string.button_9)
                    } else {
                        calc_display.append(getString(R.string.button_9))
                    }
                }

                R.id.button_ac -> calc_display.text = ""

                R.id.button_bksp -> {
                    val stringLength = calc_display.text.length
                    if (stringLength <= 1) {
                        calc_display.text = ""
                    } else {
                        calc_display.text = calc_display.text.substring(0, stringLength - 1)
                    }
                }
            }
        }
}

//    fun zeroBtn(view: View) {
//        previousValue = display.text.toString()
//        display.text = "0"
//        Log.d(TAG, "previousValue=${previousValue}")
//    }
//
//    fun oneBtn(view: View) {
//        view.setOnClickListener {
//            previousValue = display.text.toString()
//            display.text = "1"
//            Log.d(TAG, "previousValue=${previousValue}")
//        }
//    }
//
//    fun twoBtn(view: View) {
//        previousValue = display.text.toString()
//        display.text = "2"
//        Log.d(TAG, "previousValue=${previousValue}")
//    }
//
//    fun threeBtn(view: View) {
//        previousValue = display.text.toString()
//        display.text = "3"
//        Log.d(TAG, "previousValue=${previousValue}")
//    }
//
//    fun fourBtn(view: View) {
//        previousValue = display.text.toString()
//        display.text = "4"
//        Log.d(TAG, "previousValue=${previousValue}")
//    }
//
//    fun fiveBtn(view: View) {
//        previousValue = display.text.toString()
//        display.text = "5"
//        Log.d(TAG, "previousValue=${previousValue}")
//    }
//
//    fun sixBtn(view: View) {
//        previousValue = display.text.toString()
//        display.text = "6"
//        Log.d(TAG, "previousValue=${previousValue}")
//    }
//
//    fun sevenBtn(view: View) {
//        previousValue = display.text.toString()
//        display.text = "7"
//        Log.d(TAG, "previousValue=${previousValue}")
//    }
//
//    fun eightBtn(view: View) {
//        previousValue = display.text.toString()
//        display.text = "8"
//        Log.d(TAG, "previousValue=${previousValue}")
//    }
//
//    fun nineBtn(view: View) {
//        previousValue = display.text.toString()
//        display.text = "9"
//        Log.d(TAG, "previousValue=${previousValue}")
//    }

    fun allClearBtn(view: View) {

    }

    fun clearBtn(view: View) {}

    fun backspaceBtn(view: View) {
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val string = tvResult.text.toString()
        if (string.isNotEmpty()) {
            tvResult.text = string.substring(0, string.length - 1)
        }
    }

    fun changeSignBtn(view: View) {}

    fun divideBtn(view: View) {

    }

    fun subtractBtn(view: View) {

    }

    fun multiplyBtn(view: View) {

    }

    fun resultBtn(view: View) {
    }

    fun addBtn(view: View) {
    }

    fun dotBtn(view: View) {}


}
