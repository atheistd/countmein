package com.kinkykeys.countmein

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class home : AppCompatActivity() {

    // new code, who dis?
    public val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        // idk what this is, just copy-pasta
        val context = this

        // connect variables to UI elements from activity_main.xml to here
        val home_show_uid = findViewById(R.id.show_user_name) as TextView
        val temp_scan_button = findViewById(R.id.scan_code_button) as Button
        val temp_stat_button = findViewById(R.id.status_button) as Button

        // same voodoo magic from MainActivity
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val home_uid = sharedPreferences.getString("saved_uid", "default_uid")

        home_show_uid.setText("Welcome, ${home_uid}").toString()

        temp_scan_button.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, scan::class.java)
            startActivity(intent)
        })

        temp_stat_button.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, disaster_forecast::class.java)
            startActivity(intent)
        })

    }
}