
package com.kinkykeys.countmein

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.system.StructTimespec
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // connect variables to UI elements from activity_main.xml to here
        val var_uid = findViewById(R.id.takes_uid) as EditText
        val var_passwd = findViewById(R.id.takes_passwd) as EditText
        val login_button = findViewById(R.id.login_button) as Button


        // SQLite related init
        val context = this
        val db = DataBaseHandler(context)

        // read the table with credentials in it
        val creds = db.readCred()
        val creds_len = creds.size

        if (creds_len == 0) {
            // do nothing, the cred table is empty
        } else {
            val intent = Intent(this, home::class.java)
            startActivity(intent)
        }


        // user enters credentials and gets logged in (without passwd verification for the moment)
        login_button.setOnClickListener(View.OnClickListener {

            db.clearCreds()

            // declare more (temp) variables
            val uid = var_uid.text.toString()
            val passwd = var_passwd.text.toString()


            var user = User()
            user.dbUID = uid
            user.dbPasswd = passwd
            db.insertCred(user)

            //  segue into "home" activity
            val intent = Intent(this, home::class.java)
            startActivity(intent)

        })


    }
}