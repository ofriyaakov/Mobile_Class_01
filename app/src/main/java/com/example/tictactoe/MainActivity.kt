package com.example.tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var currentPlayer = "X"
    private lateinit var winMessage: TextView

    private lateinit var position1: Button
    private lateinit var position2: Button
    private lateinit var position3: Button
    private lateinit var position4: Button
    private lateinit var position5: Button
    private lateinit var position6: Button
    private lateinit var position7: Button
    private lateinit var position8: Button
    private lateinit var position9: Button

    private lateinit var grid: Array<Array<Button>>

    private lateinit var newGameButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        position1 = findViewById(R.id.button00)
        position2 = findViewById(R.id.button01)
        position3 = findViewById(R.id.button02)
        position4 = findViewById(R.id.button10)
        position5 = findViewById(R.id.button11)
        position6 = findViewById(R.id.button12)
        position7 = findViewById(R.id.button20)
        position8 = findViewById(R.id.button21)
        position9 = findViewById(R.id.button22)

        winMessage = findViewById(R.id.winMessage)

        grid = arrayOf(
            arrayOf(position1, position2, position3),
            arrayOf(position4, position5, position6),
            arrayOf(position7, position8, position9)
        )


        disableAllCells()

        newGameButton = findViewById(R.id.newGameButton)

        newGameButton.setOnClickListener {
            resetGame()
        }

    }

    private fun resetGame() {
        currentPlayer = "X"
        newGameButton.isEnabled = false
        newGameButton.text = "Player X turn"
        winMessage.text = ""

        for (i in 0..2) {
            for (j in 0..2) {
                grid[i][j].text = ""
                grid[i][j].isEnabled = true
                grid[i][j].setOnClickListener {
                    makeMove(grid[i][j])
                }
            }
        }
    }

    private fun makeMove(cell: Button){
        cell.text = currentPlayer
        cell.isEnabled = false
        val winner = checkWin()

        if (winner == "") {
            val isTie = checkTie()
            if (isTie) {
                winMessage.text = "It's a tie!"
                newGameButton.text = "New Game"
                newGameButton.isEnabled = true
                return
            }
        } else if (winner == "X" || winner == "O"){
            disableAllCells()
            winMessage(winner)
            newGameButton.text = "New Game"
            newGameButton.isEnabled = true
            return
        }

        currentPlayer = if (currentPlayer == "X") "O" else "X"
        newGameButton.text = "Player $currentPlayer's turn"
    }

    private fun checkTie(): Boolean {
        for (i in 0..2){
            for (j in 0..2) {
                if (grid[i][j].text.toString() == "") return false
            }
        }

        return true
    }

    private fun checkWin(): String {
        if (grid[0][0].text == grid[1][1].text && grid[0][0].text == grid[2][2].text
            && grid[0][0].text !== ""
        ) return grid[0][0].text.toString()
        if (grid[0][2].text == grid[1][1].text && grid[0][2].text == grid[2][0].text
            && grid[0][2].text !== ""
        ) return grid[0][2].text.toString()


        for (i in 0..2) {
            if (grid[i][0].text == grid[i][1].text && grid[i][0].text == grid[i][2].text
                && grid[i][0].text !== ""
            ) return grid[i][0].text.toString()
            if (grid[0][i].text == grid[1][i].text && grid[0][i].text == grid[2][i].text
                && grid[0][i].text !== ""
            ) return grid[0][i].text.toString()
        }

        return ""
    }

    private fun winMessage(winChar: String) {
        if (winChar === "X") winMessage.text = "Player X win!"
        else winMessage.text = "Player O win!"
    }

    private fun disableAllCells() {
        for (i in 0..2) {
            for (j in 0..2) {
                grid[i][j].isEnabled = false
            }
        }
    }
}