package com.kinkykeys.countmein

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class scan : AppCompatActivity() {

    // SQLite related init
    // as this needs to be used in multiple methods
    // it is best to make this a global thing
    // but is not safe; Tread carefully, Maze.
    val context = this
    val db = DataBaseHandler(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scan)

        // QR Code scanner related init
        val scanner = IntentIntegrator(this)
        scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        scanner.setCameraId(0)
        scanner.setPrompt("Scan the subject QR Code now")
        scanner.setBeepEnabled(false)
        scanner.setBarcodeImageEnabled(false)
        scanner.setOrientationLocked(true)
        scanner.initiateScan()
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

                // if there is any error, this go back to the home intent for scanning the code again
                Toast.makeText(this, "An error occurred. Please try again.", Toast.LENGTH_LONG).show()

                // goto the home intent by ending current activity
                finish()

            } else {

                // get the local time of scan
                val curr_date_time = LocalDateTime.now()
                // pattern for formatting curr_date_time later on
                val fmt_date = DateTimeFormatter.ofPattern("dd-MM")
                val fmt_time = DateTimeFormatter.ofPattern("HH:mm:ss")
                // formatting curr_date_time with previously defined patterns in two new variables
                val curr_date = fmt_date.format(curr_date_time)
                val curr_time = fmt_time.format(curr_date_time)

                // save the scanned data in subject as a string
                val subject = result.getContents().toString()

                var user = User()
                user.dbDate = curr_date
                user.dbTime = curr_time
                user.dbSub = subject
                db.insertData(user)

                Toast.makeText(this, "QR Code is scanned and the attendance should be updated shortly.", Toast.LENGTH_LONG).show()

                // goto the attendance intent to verify the
                val intent = Intent(this, disaster_forecast::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)

                finish()

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }


    }
}