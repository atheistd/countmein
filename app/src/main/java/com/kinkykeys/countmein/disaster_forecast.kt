package com.kinkykeys.countmein

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class disaster_forecast : AppCompatActivity() {

    // SQLite related init
    // as this needs to be used in multiple methods
    // it is best to make this a global thing
    // but is not safe; Tread carefully, Maze.
    val context = this
    val db = DataBaseHandler(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.disaster_forecast)

        val attendance_view = findViewById(R.id.disaster_out) as TextView

        // retrieve values
        var attendance = db.readData()
        attendance_view.text = ""

        var temp_day = ""
        var table_size = attendance.size
        var i = 0

        // fetch dbUID
        var cred = db.readCred()
        val dbuid_tosave = cred[0].dbUID

        fun nodata() {
            attendance_view.append("No data available at this moment.")
            attendance_view.append("\nPlease scan the QR Codes to populate this view.")
            attendance_view.append("\n\nIf this problem still persists even after scanning QR Code(s), please contact your administrator.")
        }

        if (dbuid_tosave.isNullOrEmpty()) {

            } else {
                if (table_size > 0) {
                    for (entry in attendance) {
                        if (dbuid_tosave.equals(entry.dbUID.toString(), ignoreCase = true)) {
                            if (!temp_day.equals(entry.dbDate.toString(), ignoreCase = true)) {
                                attendance_view.append("\n\n" + entry.dbDate.toString())
                                temp_day = entry.dbDate.toString()
                            }
                            attendance_view.append("\nScan time:\t" + entry.dbTime.toString() + "\tSubject:\t" + entry.dbSub.toString())
                        } else {
                            nodata()
                        }
                    }

            }
            nodata()
            attendance_view.append("\n\n\nWARNING: Empty/NULL UID.\nCheckback with your administrator.")
        }



    }
}