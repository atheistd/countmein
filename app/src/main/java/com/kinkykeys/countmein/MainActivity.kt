
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
        val s_login_button = findViewById(R.id.student_login_button) as Button
        val t_login_button = findViewById(R.id.teacher_login_button) as Button


        // SQLite related init
        val context = this
        val db = DataBaseHandler(context)

        /* commented out this code
           as I do not want to implement
           logout for such silly thing

        // read the table with credentials in it
        val credsS = db.readCred()
        val credsS_len = credsS.size

        if (credsS_len == 0) {
            // do nothing, the cred table is empty
        } else {
            val intent = Intent(this, home::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

            finish()
        }
        val credsT = db.readCred()
        val credsT_len = credsT.size

        if (credsT_len == 0) {
            // do nothing, the cred table is empty
        } else {
            val intent = Intent(this, f_home::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

            finish()
        }
        */


        // user enters credentials and gets logged in (without passwd verification for the moment)
        s_login_button.setOnClickListener(View.OnClickListener {

            db.clearCreds()


            var user = User()
            user.dbUID = var_uid.text.toString()
            user.dbPasswd = var_passwd.text.toString()
            db.insertCred(user)

            //  segue into "home" activity
            val intent = Intent(this, home::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

            finish()

        })
        t_login_button.setOnClickListener(View.OnClickListener {

            db.clearTCreds()


            var user = Teacher()
            user.dbUID = var_uid.text.toString()
            user.dbPasswd = var_passwd.text.toString()
            db.insertCred(user)

            //  segue into "home" activity
            val intent = Intent(this, f_home::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

            finish()

        })


    }
}