package com.kinkykeys.countmein

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.system.StructTimespec
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // new code, who dis?
    public val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // connect variables to UI elements from activity_main.xml to here
        val var_uid = findViewById(R.id.takes_uid) as EditText
        val var_passwd = findViewById(R.id.takes_passwd) as EditText
        val login_button = findViewById(R.id.login_button) as Button

        // idk wtf this is, but is necessary lol
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        // verify if credentials are already stored
        val shared_uid = sharedPreferences.getString("saved_uid", "default_uid")
        val shared_passwd = sharedPreferences.getString("saved_passwd", "default_passwd")

        if(shared_uid.isNullOrEmpty()) {
            // do nothing
        } else {
            val intent = Intent(this, home::class.java)
            startActivity(intent)
        }

        // idk what this is, just copy-pasta
        val context = this

        // user enters credentials and gets logged in (without passwd verification for the moment)
        login_button.setOnClickListener(View.OnClickListener {

            // something db related, idk what;
            // var studentMA = student(var_uid.text.toString())
            // var db = DataBaseHandler(context)
            // db.insertData(studentMA)

            // declare more (temp) variables
            val uid: String = var_uid.text.toString()
            val passwd: String = var_passwd.text.toString()

            // commence editing session initalisation
            val editor:SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putString("saved_uid", uid)
            editor.putString("saved_passwd", passwd)

            // git commit lol
            editor.apply()
            editor.commit()

            //  segue into "home" activity
            val intent = Intent(this, home::class.java)
            startActivity(intent)
        })


    }
}