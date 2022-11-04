package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

private const val TAG = "BasicCalculator"

class BasicCalculator : AppCompatActivity() {
    private lateinit var calcDisplay: TextView
    var firstValue: Double = 0.0
    lateinit var operation: String
    var lastOperationPressed: Boolean = false
    var dotInValue: Boolean = false
    var resultValue: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basic_calculator)

        calcDisplay = findViewById<TextView>(R.id.tvResult)
        calcDisplay.text = "0"
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
            "Digit: ${(view as Button).text}, String: ${calcDisplay.text}, lastOperationPressed: ${lastOperationPressed}, dotInValue: $dotInValue"
        )
    }

    fun changeSignBtn(view: View?) {
        var displayedValue = calcDisplay.text.toString().toDouble()
        displayedValue = if (displayedValue > 0) -displayedValue else abs(displayedValue)

        //jeśli reszta dzielenia wartości przez 1 wynosi 0.0 lub -0.0
        if (displayedValue.rem(1).equals(0.0) or displayedValue.rem(1).equals(-0.0)) {
            calcDisplay.text = displayedValue.toInt().toString()
        } else {
            calcDisplay.text = displayedValue.toString()
        }
    }

    fun operationButtonHandler(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.button_add -> operation = "+"
                R.id.button_subtract -> operation = "-"
                R.id.button_multiply -> operation = "*"
                R.id.button_divide -> operation = "/"
            }
        }
        lastOperationPressed = true;
        firstValue = calcDisplay.text.toString().toDouble()
        Log.d(
            TAG,
            "Operation: ${operation}, lastOperationPressed: ${lastOperationPressed}, firstValue: ${firstValue}, dotInValue: $dotInValue"
        )
    }

    //TODO add functionality to another number!
    fun dotBtn(view: View) {
        if (!dotInValue) {
            calcDisplay.append((view as Button).text)
            dotInValue = true
        }
    }

    fun allClearBtn(view: View?) {
        Log.d(TAG, "Event handler call: ${calcDisplay.text}")
        calcDisplay.text = "0"
        dotInValue = false
    }

    fun bkspBtn(view: View?) {
        Log.d(TAG, "Event handler call: ${calcDisplay.text}")
        val stringLength = calcDisplay.text.length
        if (stringLength <= 1) {
            calcDisplay.text = "0"
        } else {
            if (calcDisplay.text.toString().last() == '.') {
                dotInValue = false
            }
            calcDisplay.text = calcDisplay.text.substring(0, stringLength - 1)
        }
    }

    fun resultBtn(view: View?) {
        val secondValue: Double = calcDisplay.text.toString().toDouble()

        when (operation) {
            "+" -> resultValue = firstValue + secondValue
            "-" -> resultValue = firstValue - secondValue
            "*" -> resultValue = firstValue * secondValue
            "/" -> {
                if (secondValue != 0.0) {
                    resultValue = firstValue / secondValue
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Oops! You better do not divide by 0!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        if (resultValue.rem(1).equals(0.0) or resultValue.rem(1).equals(-0.0)) {
            calcDisplay.text = resultValue.toInt().toString()
        } else {
            calcDisplay.text = resultValue.toString()
        }
        dotInValue = resultValue.toString().contains('.')
        Log.d(
            TAG,
            "Result: ${resultValue}, dotInValue: ${dotInValue}"
        )
    }
}

