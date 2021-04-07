package com.kinkykeys.countmein

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class f_atnmod : AppCompatActivity() {

    // new code, who dis?
    public val sharedPrefFile = "kotlinsharedpreference"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_f_atnmod)

        // SQLite related init
        val context = this
        val db = DataBaseHandler(context)


        // connect variables to UI elements from activity_main.xml to here
        val acpt_suid = findViewById(R.id.takes_suid) as EditText
        val acpt_sub = findViewById(R.id.takes_subject) as EditText
        val acpt_date = findViewById(R.id.takes_date) as EditText
        val acpt_time = findViewById(R.id.takes_time) as EditText
        val modify_database_button = findViewById(R.id.mod_db) as Button


        modify_database_button.setOnClickListener(View.OnClickListener {

            // SQLite related init
            val context = this
            val db = DataBaseHandler(context)

            var user = User()
            user.dbUID = acpt_suid.text.toString()
            user.dbSub = acpt_sub.text.toString()
            user.dbDate = acpt_date.text.toString()
            user.dbTime = acpt_time.text.toString()
            db.insertData(user)

            // goto the attendance intent to verify the
            val intent = Intent(this, f_atnview::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

            finish()

        })

    }
}