package com.cse489.vangtachai

import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // The notes used for change calculation (largest first)
    private val notes = listOf(500, 100, 50, 20, 10, 5, 2, 1)

    // Current entered amount (stored as Long to handle large numbers)
    private var currentAmount: Long = 0L

    // Key for saving state
    private companion object {
        const val KEY_AMOUNT = "current_amount"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Restore saved state if available
        if (savedInstanceState != null) {
            currentAmount = savedInstanceState.getLong(KEY_AMOUNT, 0L)
        }

        setupKeypad()
        updateDisplay()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_AMOUNT, currentAmount)
    }

    /**
     * Sets up click listeners for all numeric buttons and the clear button.
     */
    private fun setupKeypad() {
        // Map button IDs to their digit values
        val digitButtons = mapOf(
            R.id.btn_0 to 0,
            R.id.btn_1 to 1,
            R.id.btn_2 to 2,
            R.id.btn_3 to 3,
            R.id.btn_4 to 4,
            R.id.btn_5 to 5,
            R.id.btn_6 to 6,
            R.id.btn_7 to 7,
            R.id.btn_8 to 8,
            R.id.btn_9 to 9
        )

        for ((btnId, digit) in digitButtons) {
            findViewById<Button>(btnId).setOnClickListener {
                onDigitPressed(digit)
            }
        }

        // Clear button resets the amount
        findViewById<Button>(R.id.btn_clear).setOnClickListener {
            onClearPressed()
        }
    }

    /**
     * Called when a digit button is pressed.
     * Appends the digit to the right of the current amount.
     * E.g., current=23, digit=4 → new amount=234
     */
    private fun onDigitPressed(digit: Int) {
        // Guard against overflow: limit to 9 digits
        if (currentAmount > 999_999_99L) return
        currentAmount = currentAmount * 10 + digit
        updateDisplay()
    }

    /**
     * Called when the clear button is pressed. Resets the amount to 0.
     */
    private fun onClearPressed() {
        currentAmount = 0L
        updateDisplay()
    }

    /**
     * Updates both the amount TextView and the change table.
     */
    private fun updateDisplay() {
        updateAmountDisplay()
        updateChangeTable()
    }

    /**
     * Updates the "Taka: X" display at the top.
     */
    private fun updateAmountDisplay() {
        val amountTextView = findViewById<TextView>(R.id.tv_amount)
        amountTextView.text = currentAmount.toString()
    }

    /**
     * Calculates and updates the change breakdown table.
     * Uses a greedy algorithm with notes [500, 100, 50, 20, 10, 5, 2, 1].
     */
    private fun updateChangeTable() {
        var remaining = currentAmount
        val changeBreakdown = mutableListOf<Pair<Int, Long>>()

        for (note in notes) {
            val count = remaining / note
            remaining -= count * note
            changeBreakdown.add(Pair(note, count))
        }

        // Update the count TextViews for each note denomination
        updateNoteCount(R.id.tv_count_500, changeBreakdown[0].second)
        updateNoteCount(R.id.tv_count_100, changeBreakdown[1].second)
        updateNoteCount(R.id.tv_count_50, changeBreakdown[2].second)
        updateNoteCount(R.id.tv_count_20, changeBreakdown[3].second)
        updateNoteCount(R.id.tv_count_10, changeBreakdown[4].second)
        updateNoteCount(R.id.tv_count_5, changeBreakdown[5].second)
        updateNoteCount(R.id.tv_count_2, changeBreakdown[6].second)
        updateNoteCount(R.id.tv_count_1, changeBreakdown[7].second)
    }

    /**
     * Updates a note count TextView, hiding zero counts for clarity.
     */
    private fun updateNoteCount(viewId: Int, count: Long) {
        val textView = findViewById<TextView>(viewId)
        textView.text = count.toString()
    }
}
