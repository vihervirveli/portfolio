package com.example.e04sumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.util.Log

class MainActivity : AppCompatActivity() {
    private var number: Int = 0
    private var sum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun numberInput(view: View){
        val btnId = view.id
        var digit = 0
        when (btnId){
            R.id.buttonzero -> digit = 0
            R.id.button1 -> digit = 1
            R.id.button2 -> digit = 2
            R.id.button3 -> digit = 3
            R.id.button4 -> digit = 4
            R.id.button5 -> digit = 5
            R.id.button6 -> digit = 6
            R.id.button7 -> digit = 7
            R.id.button8 -> digit = 8
            R.id.button9 -> digit = 9

        }
        val editText = findViewById<EditText>(R.id.text_input)
        var oldText = editText.text.toString()
        if (oldText == "0") {
            editText.setText(digit.toString())
            return
        }
        editText.append(digit.toString())
    }
    fun clearField(view:View){

        val editText = findViewById<EditText>(R.id.text_input)
        val default = 0
        editText.setText(default.toString());

    }

    fun addPlus(view: View){
        try {
        val editText = findViewById<EditText>(R.id.text_input)
        editText.append("+")
        } catch (e: Exception) {
            Log.e("MyApp", "clearField error: ${e.message}", e)
        }
    }

    fun sumResult(view: View){
        try{
        val editText = findViewById<EditText>(R.id.text_input)
        var textString = editText.text.toString()
        val nums = textString.split("+").map{it.toInt()}
        sum = nums.sum()
        editText.setText(sum.toString())
        } catch (e: Exception) {
            Log.e("MyApp", "clearField error: ${e.message}", e)
        }
    }

}