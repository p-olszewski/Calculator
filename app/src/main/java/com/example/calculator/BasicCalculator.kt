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
    private var resultValue: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basic_calculator)

        calcDisplay = findViewById<TextView>(R.id.tvResult)
        calcDisplay.text = "0"
        firstValue = calcDisplay.text.toString().toDouble()
        Log.d(TAG, "First call: ${calcDisplay.text}")
    }

    /**
     * Calculates the result and round it to 5 decimal places.
     * Display result in scientific notation if necessary.
     * @param value1
     * @param operator
     * @param value2
     */
    private fun calculateResult(value1: Double, operator: String, value2: Double) {
        Log.d(TAG, "Result value before: $resultValue")
        when (operator) {
            "+" -> resultValue = value1 + value2
            "-" -> resultValue = value1 - value2
            "*" -> resultValue = value1 * value2
            "/" -> {
                if (value2 != 0.0) {
                    resultValue = value1 / value2
                } else {
                    Toast.makeText(
                        applicationContext, "Oops! You better do not divide by 0! Choose another number.", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        dotInValue = calcDisplay.text.toString().contains('.') // true or false
        formatResult()
        Log.d(TAG, "Result: $resultValue, $value1 $operator $value2, dotInValue: $dotInValue")
    }

    /**
     * Allow the user to enter number values.
     * Max number length is set to 10 characters (including a dot).
     * @param view
     */
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
            "Digit: ${(view as Button).text}, String: ${calcDisplay.text}, lastOperationPressed: $lastOperationPressed, dotInValue: $dotInValue"
        )
    }

    /**
     * Allow the user to select a mathematical operation.
     * Calculates the result dynamically with successive operations.
     * @param view
     */
    fun operationButtonHandler(view: View?) {
        if (!lastOperationPressed and operation.isNotEmpty()) {
            calculateResult(firstValue, operation, calcDisplay.text.toString().toDouble())
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
            "Operation: $operation, lastOperationPressed: $lastOperationPressed, firstValue: $firstValue, dotInValue: $dotInValue"
        )
    }

    /**
     * Dot button handler. Allows the user to add dot in expression.
     * Only 1 dot per expression.
     * @param view
     */
    fun dotBtn(view: View) {
        if (!dotInValue) {
            calcDisplay.append((view as Button).text)
            dotInValue = true
        }
    }

    /**
     * All Clear button handler. Allows the user to clear calculations.
     * @param view
     */
    fun allClearBtn(view: View?) {
        Log.d(TAG, "Event handler call: ${calcDisplay.text}")
        restoreDefaults()
    }

    /**
     * Backspace button handler. Allows the user to delete last character.
     * @param view
     */
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

    /**
     * Helper function. Set essential settings to default.
     */
    private fun restoreDefaults() {
        calcDisplay.text = "0"
        firstValue = 0.0
        resultValue = 0.0
        operation = ""
        dotInValue = false
    }

    /**
     * Change sign button handler. Allows the user to change the sign of a number to the opposite one.
     * @param view
     */
    fun changeSignBtn(view: View?) {
        var displayedValue = calcDisplay.text.toString().toDouble()
        displayedValue = if (displayedValue > 0) -displayedValue else abs(displayedValue)
        calcDisplay.text = displayedValue.toString()
        formatResult()
    }

    /**
     * Helper function. Format result on screen to scientific notation if needed.
     * Remove last 2 characters if result ends with '.0'.
     */
    private fun formatResult() {
        calcDisplay.text = String.format("%g", resultValue).toDouble().toString()
        if (calcDisplay.text.toString().takeLast(2) == ".0") {
            calcDisplay.text = calcDisplay.text.toString().dropLast(2)
        }
    }

    /**
     * Result button handler. Allows the user to calculate result.
     * @param view
     */
    fun resultBtn(view: View) {
        if (!lastOperationPressed) {
            calculateResult(firstValue, operation, calcDisplay.text.toString().toDouble())
            lastOperationPressed = true
        }
    }
}

