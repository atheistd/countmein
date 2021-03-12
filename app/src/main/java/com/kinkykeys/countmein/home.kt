package com.kinkykeys.countmein

import com.kinkykeys.countmein.MainActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text
import java.time.LocalDateTime
import java.time.LocalTime

class home : AppCompatActivity() {

    // new code, who dis?
    public val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        // SQLite related init
        val context = this
        val db = DataBaseHandler(context)


        // connect variables to UI elements from activity_main.xml to here
        val show_details = findViewById(R.id.home_header) as TextView
        val scan_button = findViewById(R.id.scan_code_button) as Button
        val attendance_button = findViewById(R.id.show_attendance_button) as Button


        // retrieve values
        var cred = db.readCred()
        var db_uid = cred[0].dbUID

        show_details.text = "Welcome, " + db_uid + "\n"

        scan_button.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, scan::class.java)
            startActivity(intent)
        })

        attendance_button.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, disaster_forecast::class.java)
            startActivity(intent)
        })

    }
}