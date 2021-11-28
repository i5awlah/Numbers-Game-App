package com.example.numbersgameapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nums = listOf("1","8","5","6","7","9","1","44","88")

        val myRV = findViewById<RecyclerView>(R.id.rvMain)
        myRV.adapter = numberAdaptor(nums)
        myRV.layoutManager = LinearLayoutManager(this)
    }
}