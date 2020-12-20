package com.kinkykeys.countmein

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class home : AppCompatActivity() {

    // new code, who dis?
    public val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        // connect variables to UI elements from activity_main.xml to here
        val home_show_uid = findViewById(R.id.home_show_uname) as TextView

        // same voodoo magic from MainActivity
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val home_uid = sharedPreferences.getString("saved_uid", "default_uid")

        home_show_uid.setText(home_uid).toString()

    }
}