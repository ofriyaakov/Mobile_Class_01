package com.example.tictactoe

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var currentPlayer = "X"
        var gameBoard = Array(3) { Array(3) { "" } }

        val position1: Button = findViewById(R.id.button00)
        val position2: Button = findViewById(R.id.button01)
        val position3: Button = findViewById(R.id.button02)
        val position4: Button = findViewById(R.id.button10)
        val position5: Button = findViewById(R.id.button11)
        val position6: Button = findViewById(R.id.button12)
        val position7: Button = findViewById(R.id.button20)
        val position8: Button = findViewById(R.id.button21)
        val position9: Button = findViewById(R.id.button22)

        val grid: Array<Array<Button>> = arrayOf(
            arrayOf(position1, position2, position3),
            arrayOf(position4, position5, position6),
            arrayOf(position7, position8, position9)
        )

        val winPositions: Array<Array<Button>> = arrayOf(
            arrayOf(position1, position2, position3),
            arrayOf(position4, position5, position6),
            arrayOf(position7, position8, position9),
            arrayOf(position1, position4, position7),
            arrayOf(position2, position5, position8),
            arrayOf(position3, position6, position9),
            arrayOf(position1, position5, position9),
            arrayOf(position3, position5, position7)
        )

        fun resetGame(view: View) {
            gameBoard = Array(3) { Array(3) { "" } }
            currentPlayer = "X"
            val currentPlayerText: TextView = findViewById(R.id.currentPlayerTextView)
            currentPlayerText.text = "Player X turn"

            // Clear the buttons and enable them
            for (i in 0..2) {
                for (j in 0..2) {
                    grid[i][j].text = ""
                    grid[i][j].isEnabled = true
                }
            }
        }
    }
}