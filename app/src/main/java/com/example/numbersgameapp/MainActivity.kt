package com.example.numbersgameapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
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

    private var randomNumber = 0
    private var numberOfGuess = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messages = ArrayList()

        myLayout = findViewById(R.id.clMain)

        myRV = findViewById(R.id.rvMain)
        myRV.adapter = messageAdaptor(messages)
        myRV.layoutManager = LinearLayoutManager(this)

        guessField = findViewById(R.id.etGuessField)
        guessButton = findViewById(R.id.btnGuess)
        guessButton.setOnClickListener { addMessage() }


        startGame()

    }

    private fun startGame() {
        randomNumber = Random.nextInt(11)
        numberOfGuess = 3
        messages.clear()
        myRV.adapter?.notifyDataSetChanged()
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
                    }
                    else {
                        messages.add("You guessed $userGuess")
                        messages.add("You have $numberOfGuess guesses left")
                    }

                }
                if(numberOfGuess == 0) {
                    messages.add("The Answer is: $randomNumber")
                    messages.add("Game over!")

                    showAlert()
                }
            }
            else {
                Snackbar.make(myLayout, "Please enter numbers only", Snackbar.LENGTH_LONG).show()
            }
        }
        else {
            Snackbar.make(myLayout, "You didn't enter anything!", Snackbar.LENGTH_LONG).show()
        }
        myRV.adapter?.notifyDataSetChanged()
    }
    private fun checkNumber(userNumber: String): Boolean {
        return try {
            userNumber.toInt()
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun showAlert(){
        // first we create a variable to hold an AlertDialog builder
        val dialogBuilder = AlertDialog.Builder(this)

        // here we set the message of our alert dialog
        dialogBuilder.setMessage("Do you want to play again?")
            // positive button text and action
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialog, id -> startGame()
            })
            // negative button text and action
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("New Game")
        // show alert dialog
        alert.show()
    }


}