package com.kinkykeys.countmein

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class disaster_forecast : AppCompatActivity() {

    // new code, who dis?
    public val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.disaster_forecast)

        // idk what this is, just copy-pasta
        val context = this

        // same voodoo magic from MainActivity
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val attendance_dates = sharedPreferences.getString("attendanced", "attendanceD")
        val attendance_sub = sharedPreferences.getString("attendances", "attendanceS")

        val temp = findViewById(R.id.disaster_temp) as TextView
        temp.setText(attendance_dates + "\n" + attendance_sub)

    }
}