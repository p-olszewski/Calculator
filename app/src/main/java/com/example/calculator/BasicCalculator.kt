package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "BasicCalculator"

class BasicCalculator : AppCompatActivity() {
    private lateinit var calcDisplay: TextView
    var firstValue: Double = 0.0
    lateinit var operation: String
    var lastNumeric: Boolean = false
    var lastOperationPressed: Boolean = false
    var lastDot: Boolean = false
    var resultValue: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basic_calculator)

        calcDisplay = findViewById<TextView>(R.id.tvResult)
        calcDisplay.text = resultValue.toString()
        firstValue = calcDisplay.text.toString().toDouble()
        Log.d(TAG, "First call: ${calcDisplay.text}")
    }

    fun numericButtonHandler(view: View?) {
        if (lastOperationPressed or (calcDisplay.text.toString() == "0")) {
            calcDisplay.text = (view as Button).text
        } else {
            calcDisplay.append((view as Button).text)
        }
        lastOperationPressed = false
        Log.d(
            TAG,
            "Digit: ${(view as Button).text}, String: ${calcDisplay.text}, lastOperationPressed: ${lastOperationPressed}"
        )
    }

    fun changeSignBtn(view: View?) {}

    fun addBtn(view: View?) {
        lastOperationPressed = true;
        operation = "+"
        firstValue = calcDisplay.text.toString().toDouble()
        Log.d(
            TAG,
            "Operation: ${operation}, lastOperationPressed: ${lastOperationPressed}, firstValue: ${firstValue}"
        )
    }

    fun subtractBtn(view: View?) {
        lastOperationPressed = true;
        operation = "-"
        firstValue = calcDisplay.text.toString().toDouble()
        Log.d(
            TAG,
            "Operation: ${operation}, lastOperationPressed: ${lastOperationPressed}, firstValue: ${firstValue}"
        )
    }

    fun multiplyBtn(view: View?) {
        lastOperationPressed = true;
        operation = "*"
        firstValue = calcDisplay.text.toString().toDouble()
        Log.d(
            TAG,
            "Operation: ${operation}, lastOperationPressed: ${lastOperationPressed}, firstValue: ${firstValue}"
        )
    }

    fun divideBtn(view: View?) {
        lastOperationPressed = true;
        operation = "/"
        firstValue = calcDisplay.text.toString().toDouble()
        Log.d(
            TAG,
            "Operation: ${operation}, lastOperationPressed: ${lastOperationPressed}, firstValue: ${firstValue}"
        )
    }

    fun dotBtn(view: View) {}

    fun allClearBtn(view: View?) {
        Log.d(TAG, "Event handler call: ${calcDisplay.text}")
        calcDisplay.text = "0"
        lastNumeric = false
        lastDot = false
    }

    fun bkspBtn(view: View?) {
        Log.d(TAG, "Event handler call: ${calcDisplay.text}")
        val stringLength = calcDisplay.text.length
        if (stringLength <= 1) {
            calcDisplay.text = "0"
        } else {
            calcDisplay.text = calcDisplay.text.substring(0, stringLength - 1)
        }
    }

    fun resultBtn(view: View?) {
        val secondValue: Double = calcDisplay.text.toString().toDouble()

        when (operation) {
            "+" -> {
                resultValue = firstValue + secondValue
                calcDisplay.text = resultValue.toString()
            }
            "-" -> {
                resultValue = firstValue - secondValue
                calcDisplay.text = resultValue.toString()
            }
            "*" -> {
                resultValue = firstValue * secondValue
                calcDisplay.text = resultValue.toString()
            }
//            "/" -> {
//                if (secondValue != 0) {
//                    resultValue = firstValue / secondValue
//                    calcDisplay.text = resultValue.toString()
//                }
        }
    }
}

