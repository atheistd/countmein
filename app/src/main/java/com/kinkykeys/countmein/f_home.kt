package com.kinkykeys.countmein

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class f_home : AppCompatActivity() {

    // new code, who dis?
    public val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_f_home)

        // SQLite related init
        val context = this
        val db = DataBaseHandler(context)


        // connect variables to UI elements from activity_main.xml to here
        val fgreet = findViewById(R.id.f_header) as TextView
        val attn_view = findViewById(R.id.f_attn_show) as Button
        val attn_mod = findViewById(R.id.f_attn_mod) as Button

        // retrieve values
        var cred = db.readTCred()
        var db_uid = cred[0].dbUID

        fgreet.text = "Welcome, " + db_uid + "\n"

        attn_view.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, f_atnview::class.java)
            startActivity(intent)
        })

        attn_mod.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, f_atnmod::class.java)
            startActivity(intent)
        })

    }
}