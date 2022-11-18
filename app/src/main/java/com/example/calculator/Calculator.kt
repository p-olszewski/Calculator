package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.*

const val TAG = "Calculator"
private const val SCREENVALUE_KEY = "screenValue_key"
private const val OPERATION_KEY = "operation_key"
private const val LASTOPERATIONPRESSED_KEY = "lastOperationPressed_key"
private const val DOTINVALUE_KEY = "dotInValue_key"
private const val FIRSTVALUE_KEY = "firstValue_key"
private const val RESULTVALUE_KEY = "resultValue_key"

open class Calculator : AppCompatActivity() {
    lateinit var calcDisplay: TextView
    var firstValue: Double = 0.0
    private var operation: String = ""
    private var lastOperationPressed: Boolean = false
    private var dotInValue: Boolean = false
    private var resultValue: Double = 0.0

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(SCREENVALUE_KEY, calcDisplay.text.toString())
        outState.putString(OPERATION_KEY, operation)
        outState.putBoolean(LASTOPERATIONPRESSED_KEY, lastOperationPressed)
        outState.putBoolean(DOTINVALUE_KEY, dotInValue)
        outState.putDouble(FIRSTVALUE_KEY, firstValue)
        outState.putDouble(RESULTVALUE_KEY, resultValue)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        calcDisplay.text = savedInstanceState.getString(SCREENVALUE_KEY)
        operation = savedInstanceState.getString(OPERATION_KEY).toString()
        lastOperationPressed = savedInstanceState.getBoolean(LASTOPERATIONPRESSED_KEY)
        dotInValue = savedInstanceState.getBoolean(DOTINVALUE_KEY)
        firstValue = savedInstanceState.getDouble(FIRSTVALUE_KEY)
        resultValue = savedInstanceState.getDouble(RESULTVALUE_KEY)
        super.onRestoreInstanceState(savedInstanceState)
    }

    /**
     * Prepare display TextView and initialize firstValue variable
     */
    fun displayPrepare() {
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
                        applicationContext,
                        "Oops! You better do not divide by 0! Choose another number.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            "^" -> resultValue = value1.pow(value2)
        }
        formatResult()
        checkDot()
        Log.d(TAG, "Result: $resultValue, $value1 $operator $value2, dotInValue: $dotInValue")
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
     * Helper function. Checks if a dot is displayed on the screen
     */
    private fun checkDot() {
        dotInValue = calcDisplay.text.toString().contains('.') // true or false
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
     * Allow the user to enter number values.
     * Max number length is set to 10 characters (including a dot).
     * @param view
     */
    fun numericButtonHandler(view: View?) {
        if ((lastOperationPressed or (calcDisplay.text.toString() == "0")) and (calcDisplay.text.last() != '.')) {
            calcDisplay.text = (view as Button).text
        } else {
            if (calcDisplay.text.length >= 10) {
                Toast.makeText(
                    applicationContext,
                    "Max length is 10 characters!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                calcDisplay.append((view as Button).text)
            }
        }
        lastOperationPressed = false
        checkDot()
        Log.d(
            TAG,
            "Digit: ${(view as Button).text}, String: ${calcDisplay.text}, lastOperationPressed: $lastOperationPressed, dotInValue: $dotInValue"
        )
    }

    /**
     * Allow the user to select a mathematical operation.
     * Waiting for second argument to calculate.
     * Calculates the result dynamically with successive operations.
     * @param view
     */
    fun delayedOperationButtonHandler(view: View?) {
        if (!lastOperationPressed and operation.isNotEmpty()) {
            calculateResult(firstValue, operation, calcDisplay.text.toString().toDouble())
        }
        if (view != null) {
            when (view.id) {
                R.id.button_add -> operation = "+"
                R.id.button_subtract -> operation = "-"
                R.id.button_multiply -> operation = "*"
                R.id.button_divide -> operation = "/"
                R.id.button_x_y -> operation = "^"
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
     * Allow the user to select a mathematical operation.
     * Calculates the result instantly after operation button pressed.
     * @param view
     */
    fun instantOperationButtonHandler(view: View?) {
        if (view != null) {
            resultValue = calcDisplay.text.toString().toDouble()
            when (view.id) {
                R.id.button_sin -> resultValue = sin(resultValue)
                R.id.button_cos -> resultValue = cos(resultValue)
                R.id.button_tan -> resultValue = tan(resultValue)
                R.id.button_sqrt -> resultValue = sqrt(resultValue)
                R.id.button_x_2 -> resultValue = resultValue.pow (2)
                R.id.button_log -> resultValue = log10(resultValue)
                R.id.button_ln -> resultValue = ln(resultValue)
            }
        }
        calcDisplay.text = resultValue.toString()
        checkDot()
        formatResult()
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
     * Change sign button handler. Allows the user to change the sign of a number to the opposite one.
     * @param view
     */
    fun changeSignBtn(view: View?) {
        resultValue = calcDisplay.text.toString().toDouble()
        Log.d(TAG, "Displayed value before: $resultValue")
        resultValue = if (resultValue > 0) -resultValue else abs(resultValue)
        Log.d(TAG, "Displayed value after: $resultValue")
        calcDisplay.text = resultValue.toString()
        formatResult()
    }


    /**
     * Result button handler. Allows the user to calculate result.
     * @param view
     */
    fun resultBtn(view: View) {
        Log.d(TAG, "lastOperationPressed: $lastOperationPressed")
        if (!lastOperationPressed) {
            calculateResult(firstValue, operation, calcDisplay.text.toString().toDouble())
            lastOperationPressed = true
        }
    }

    /**
     * Percent button handler. Allows the user to calculate percent value.
     * @param view
     */
    fun percentBtn(view: View) {
        resultValue = calcDisplay.text.toString().toDouble()
        resultValue /= 100
        calcDisplay.text = resultValue.toString()
        formatResult()
    }
}