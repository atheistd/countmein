package com.kinkykeys.countmein

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.DatabaseErrorHandler
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import java.time.LocalDateTime

class scan : AppCompatActivity() {

    // new code, who dis?
    public val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scan)


        // idk what this is, just copy-pasta
        val context = this


        // THE BELOW CODE WAS/IS INTENDED AS A PLACEHOLDER WHEN THERE IS NO SCAN VIEW
        // val temp = findViewById(R.id.scan_temp) as TextView
        // temp.setText("This view will open a camera for scanning the QR Code once the module is updated.")

        val scanner = IntentIntegrator(this)
        scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        scanner.setBeepEnabled(false)
        scanner.setOrientationLocked(false)
        scanner.initiateScan()
    }

    fun goto_home() {

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        val result =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        val curr_time = java.util.Calendar.getInstance().toString()


        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "An error occurred. Please try again.", Toast.LENGTH_LONG).show()

                // goto the home intent
                val intent = Intent(this, home::class.java)
                startActivity(intent)

            } else {

                // get the local time of scan
                val curr_time = LocalDateTime.now().toString()
                // save the scanned data in subject_code as a string
                var subject_code = result.getContents().toString()


                // array to store the attendance details
                //var attendanceD = charArrayOf()
                //var attendanceS:String? = arrayOf(subject_code)



                // commence editing session initalisation
                val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor =  sharedPreferences.edit()
                //editor.putString("attendanced", attendanceD)
                //editor.putString("attendances", attendanceS)

                // git commit lol
                editor.apply()
                editor.commit()

                Toast.makeText(this, "QR Code is scanned and the attendance should be updated shortly. " +curr_time, Toast.LENGTH_LONG).show()

                // goto the attendance intent to verify the
                val intent = Intent(this, disaster_forecast::class.java)
                startActivity(intent)

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }


    }
}