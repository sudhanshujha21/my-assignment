package com.example.ezetap_assignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ezetap_assignment.R
import com.example.ezetap_assignment.databinding.ActivityTwoBinding

class ActivityTwo : AppCompatActivity() {

    private lateinit var bindingTwo: ActivityTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
        bindingTwo = ActivityTwoBinding.inflate(layoutInflater)
        setContentView(bindingTwo.root)

        //get intent object, and get data from intent.

        val intent = intent
        val name = intent.getStringExtra("Name")
        val phone = intent.getStringExtra("Phone")
        val city = intent.getStringExtra("City")
        bindingTwo.nameTxt.text = name
        bindingTwo.phoneTxt.text = phone
        bindingTwo.cityTxt.text = city


    }
}