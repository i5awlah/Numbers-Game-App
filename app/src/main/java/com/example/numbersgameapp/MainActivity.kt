package com.example.numbersgameapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var myLayout: ConstraintLayout
    private lateinit var myRV: RecyclerView
    lateinit var guessButton: Button
    lateinit var guessField: EditText
    lateinit var messages: ArrayList<String>

    var randomNumber = 0
    var numberOfGuess = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myLayout = findViewById(R.id.clMain)

        randomNumber = Random.nextInt(11)

        guessField = findViewById(R.id.etGuessField)
        guessButton = findViewById(R.id.btnGuess)
        guessButton.setOnClickListener { addMessage() }

        messages = ArrayList()

        myRV = findViewById(R.id.rvMain)
        myRV.adapter = messageAdaptor(messages)
        myRV.layoutManager = LinearLayoutManager(this)
    }
    private fun addMessage() {
        val userGuess = guessField.text.toString()
        guessField.text.clear()
        guessField.clearFocus()

        if (userGuess.isNotEmpty()) {
            if (checkNumber(userGuess)) {
                if(numberOfGuess > 0) {
                    numberOfGuess--
                    if (userGuess.toInt() == randomNumber) {
                        messages.add("You got it!")
                        myRV.adapter?.notifyDataSetChanged()
                    }
                    else {
                        messages.add("You guessed $userGuess")
                        messages.add("You have $numberOfGuess guesses left")
                        myRV.adapter?.notifyDataSetChanged()
                    }

                }
            }
            else {
                Snackbar.make(myLayout, "\"Please enter numbers only.\"", Snackbar.LENGTH_LONG).show()
            }
        }
        else {
            Snackbar.make(myLayout, "You didn't enter anything!.", Snackbar.LENGTH_LONG).show()
        }
    }
    private fun checkNumber(userNumber: String): Boolean {
        return try {
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
}