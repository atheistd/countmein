package com.kinkykeys.countmein

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class disaster_forecast : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.disaster_forecast)

        val temp = findViewById(R.id.disaster_temp) as TextView
        temp.setText("This view will show the disaster your attendance is once the module is updated.")
    }
}