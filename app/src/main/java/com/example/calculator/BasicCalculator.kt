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
    private var operation: String = ""
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

    //TODO add validation to adding to numbers with e notation
    // f.e. 1.23E13+0,5 = 1.23E13, but result is calculated properly
    // reading from screen is a problem, should be read from variable
    private fun calculateResult(value1: Double, operator: String, value2: Double) {
        Log.d(TAG, "Result value before: ${resultValue}")
        when (operator) {
            "+" -> resultValue = value1 + value2
            "-" -> resultValue = value1 - value2
            "*" -> resultValue = value1 * value2
            "/" -> {
                if (value2 != 0.0) {
                    resultValue = value1 / value2
                } else {
                    Toast.makeText(
                        applicationContext, "Oops! You better do not divide by 0!", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        if (resultValue.rem(1).equals(0.0) or resultValue.rem(1).equals(-0.0)) {
            calcDisplay.text = resultValue.toLong().toString()
        } else {
            calcDisplay.text = resultValue.toString()
        }
        if (calcDisplay.text.length > 10) {
            calcDisplay.text = String.format("%e", resultValue).toDouble().toString()
        }

        dotInValue = calcDisplay.text.toString().contains('.') // true or false
        Log.d(TAG, "Result: ${resultValue}, $value1 $operator $value2, dotInValue: $dotInValue")
    }

    fun numericButtonHandler(view: View?) {
        if (lastOperationPressed or (calcDisplay.text.toString() == "0")) {
            calcDisplay.text = (view as Button).text
        } else {
            if (calcDisplay.text.length >= 10) {
                Toast.makeText(applicationContext, "Max length is 10 characters!", Toast.LENGTH_SHORT).show()
            } else {
                calcDisplay.append((view as Button).text)
            }
        }
        lastOperationPressed = false
        dotInValue = calcDisplay.text.toString().contains('.') // true or false
        Log.d(
            TAG,
            "Digit: ${(view as Button).text}, String: ${calcDisplay.text}, lastOperationPressed: ${lastOperationPressed}, dotInValue: $dotInValue"
        )
    }

    fun operationButtonHandler(view: View?) {
        if (!lastOperationPressed and operation.isNotEmpty()) {
            if (calcDisplay.text.toString().contains('E')) {
                calculateResult(firstValue, operation, resultValue)
            } else {
                calculateResult(firstValue, operation, calcDisplay.text.toString().toDouble())
            }
        }
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

    fun dotBtn(view: View) {
        if (!dotInValue) {
            calcDisplay.append((view as Button).text)
            dotInValue = true
        }
    }

    fun allClearBtn(view: View?) {
        Log.d(TAG, "Event handler call: ${calcDisplay.text}")
        restoreDefaults()
    }

    fun bkspBtn(view: View?) {
        Log.d(TAG, "Event handler call: ${calcDisplay.text}")
        val stringLength = calcDisplay.text.length
        if (stringLength <= 1) {
            restoreDefaults()
        } else {
            if (calcDisplay.text.toString().last() == '.') {
                dotInValue = false
            }
            calcDisplay.text = calcDisplay.text.substring(0, stringLength - 1)
        }
    }

    private fun restoreDefaults() {
        calcDisplay.text = "0"
        firstValue = 0.0
        resultValue = 0.0
        operation = ""
        dotInValue = false
    }

    fun changeSignBtn(view: View?) {
        var displayedValue = calcDisplay.text.toString().toDouble()
        displayedValue = if (displayedValue > 0) -displayedValue else abs(displayedValue)

        //jeśli reszta dzielenia wartości przez 1 wynosi 0.0 lub -0.0
        if (displayedValue.rem(1).equals(0.0) or displayedValue.rem(1).equals(-0.0)) {
            calcDisplay.text = displayedValue.toLong().toString()
        } else {
            calcDisplay.text = displayedValue.toString()
        }
    }

    fun resultBtn(view: View) {
        if (!lastOperationPressed) {
            if (calcDisplay.text.toString().contains('E')) {
                calculateResult(firstValue, operation, resultValue)
            } else {
                calculateResult(firstValue, operation, calcDisplay.text.toString().toDouble())
            }
            lastOperationPressed = true
        }
    }
}

