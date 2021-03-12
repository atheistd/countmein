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

        // idk what this is, just copy-pasta
        val context = this

        // connect variables to UI elements from activity_main.xml to here
        val home_show_details = findViewById(R.id.show_user_name) as TextView
        val temp_scan_button = findViewById(R.id.scan_code_button) as Button
        val temp_stat_button = findViewById(R.id.status_button) as Button
        // val s_sub_name:TextView = findViewById(R.id.last_sub_name) as TextView

        // SQL init
        val db = DataBaseHandler(context)

        // retrieve values
        var cred = db.readCred()
        var initCred = cred[0]
        var db_uid = initCred.dbUID

        home_show_details.text = "Welcome, " + db_uid + "\n"

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