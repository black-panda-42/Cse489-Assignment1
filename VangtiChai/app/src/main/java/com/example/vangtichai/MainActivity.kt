package com.example.vangtichai

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * VangtiChai – Bangladeshi Taka change calculator.
 *
 * The user enters an amount via an on-screen numeric keypad.
 * The app calculates how many notes of each denomination
 * (500, 100, 50, 20, 10, 5, 2, 1 Taka) make up that amount and
 * displays the breakdown alongside the keypad.
 *
 * Layout variants (all share the same view IDs so a single Activity works for all):
 *   res/layout/              – phone portrait   (3-col keypad, 1-col notes on left)
 *   res/layout-land/         – phone landscape  (4-col keypad, 2-col notes on left)
 *   res/layout-sw600dp/      – tablet portrait  (3-col keypad, 1-col notes on left, scaled)
 *   res/layout-sw600dp-land/ – tablet landscape (4-col keypad, 2-col notes on left, scaled)
 *
 * State is preserved across orientation changes via onSaveInstanceState /
 * onCreate(savedInstanceState), which also exercises the Activity lifecycle
 * as required by the assignment.
 */
class MainActivity : AppCompatActivity() {

    // ── State ──────────────────────────────────────────────────────────────
    private var enteredAmount: Long = 0L

    // ── View references ────────────────────────────────────────────────────
    private lateinit var tvTakaAmount: TextView
    private lateinit var tvNote500: TextView
    private lateinit var tvNote100: TextView
    private lateinit var tvNote50: TextView
    private lateinit var tvNote20: TextView
    private lateinit var tvNote10: TextView
    private lateinit var tvNote5: TextView
    private lateinit var tvNote2: TextView
    private lateinit var tvNote1: TextView

    // ── Lifecycle ──────────────────────────────────────────────────────────

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bind views (IDs are identical across all layout variants)
        tvTakaAmount = findViewById(R.id.tvTakaAmount)
        tvNote500    = findViewById(R.id.tvNote500)
        tvNote100    = findViewById(R.id.tvNote100)
        tvNote50     = findViewById(R.id.tvNote50)
        tvNote20     = findViewById(R.id.tvNote20)
        tvNote10     = findViewById(R.id.tvNote10)
        tvNote5      = findViewById(R.id.tvNote5)
        tvNote2      = findViewById(R.id.tvNote2)
        tvNote1      = findViewById(R.id.tvNote1)

        // Restore amount from saved state (e.g. after rotation)
        if (savedInstanceState != null) {
            enteredAmount = savedInstanceState.getLong(KEY_AMOUNT, 0L)
        }

        setupKeypad()
        updateUI()
    }

    /**
     * Save the currently entered amount before the Activity is destroyed
     * (e.g. on rotation). Android recreates the Activity and inflates the
     * appropriate alternate layout automatically.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_AMOUNT, enteredAmount)
    }

    // ── Keypad setup ────────────────────────────────────────────────────────

    private fun setupKeypad() {
        // Map each digit button ID to its numeric value
        val digitButtonIds = listOf(
            R.id.btn0 to 0,
            R.id.btn1 to 1,
            R.id.btn2 to 2,
            R.id.btn3 to 3,
            R.id.btn4 to 4,
            R.id.btn5 to 5,
            R.id.btn6 to 6,
            R.id.btn7 to 7,
            R.id.btn8 to 8,
            R.id.btn9 to 9,
        )

        for ((id, digit) in digitButtonIds) {
            findViewById<Button>(id).setOnClickListener { appendDigit(digit) }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener { clearAmount() }
    }

    // ── Business logic ──────────────────────────────────────────────────────

    /**
     * Append [digit] to the right of the currently displayed amount.
     * E.g. entering 2 → 3 → 4 produces 2, 23, 234.
     * Capped at 11 digits to avoid Long overflow.
     */
    private fun appendDigit(digit: Int) {
        if (enteredAmount < 99_999_999_999L) {
            enteredAmount = enteredAmount * 10 + digit
            updateUI()
        }
    }

    private fun clearAmount() {
        enteredAmount = 0L
        updateUI()
    }

    /**
     * Greedy change calculation using the standard denominations.
     * Returns a map of denomination → count.
     */
    private fun calculateChange(amount: Long): Map<Int, Long> {
        val denominations = listOf(500, 100, 50, 20, 10, 5, 2, 1)
        val result = LinkedHashMap<Int, Long>()
        var remaining = amount
        for (denom in denominations) {
            result[denom] = remaining / denom
            remaining %= denom
        }
        return result
    }

    // ── UI update ───────────────────────────────────────────────────────────

    private fun updateUI() {
        tvTakaAmount.text = getString(R.string.taka_display, enteredAmount)

        val change = calculateChange(enteredAmount)
        tvNote500.text = getString(R.string.note_display, 500, change[500] ?: 0L)
        tvNote100.text = getString(R.string.note_display, 100, change[100] ?: 0L)
        tvNote50.text  = getString(R.string.note_display, 50,  change[50]  ?: 0L)
        tvNote20.text  = getString(R.string.note_display, 20,  change[20]  ?: 0L)
        tvNote10.text  = getString(R.string.note_display, 10,  change[10]  ?: 0L)
        tvNote5.text   = getString(R.string.note_display, 5,   change[5]   ?: 0L)
        tvNote2.text   = getString(R.string.note_display, 2,   change[2]   ?: 0L)
        tvNote1.text   = getString(R.string.note_display, 1,   change[1]   ?: 0L)
    }

    // ── Companion ───────────────────────────────────────────────────────────

    companion object {
        private const val KEY_AMOUNT = "entered_amount"
    }
}
