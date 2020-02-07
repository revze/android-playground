package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_custom_view.*

class CustomViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)

//        val btn = btn_submit
//        btn.setBorderColor(R.color.dark_blue)
//        btn.setBorderColor("#fbc531")
//        btn.setBorderWidth(5)
//        btn.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
//        btn.setBackgroundColor(Color.parseColor("#9b59b6"))
//        btn.setBackgroundColor("#e84118")
    }
}
