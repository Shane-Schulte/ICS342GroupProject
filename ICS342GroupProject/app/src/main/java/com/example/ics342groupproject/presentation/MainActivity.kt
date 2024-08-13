package com.example.ics342groupproject.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ics342groupproject.R
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var counterText: TextView
    private lateinit var plusButton: Button
    private lateinit var minusButton: Button
    private var counter = 0

    private val resetHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        counterText = findViewById(R.id.counterText)
        plusButton = findViewById(R.id.plusButton)
        minusButton = findViewById(R.id.minusButton)

        plusButton.setOnClickListener {
            counter++
            updateCounter()
        }

        minusButton.setOnClickListener {
            if (counter > 0) {
                counter--
                updateCounter()
            }
        }

        scheduleMidnightReset()
    }

    private fun updateCounter() {
        counterText.text = counter.toString()
    }

    private fun scheduleMidnightReset() {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            add(Calendar.DAY_OF_YEAR, 1)
        }

        val delay = calendar.timeInMillis - System.currentTimeMillis()
        resetHandler.postDelayed({
            counter = 0
            updateCounter()
            scheduleMidnightReset()
        }, delay)
    }
}
