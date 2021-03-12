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

        do {

            if (temp_day.equals(attendance[i].dbDate.toString(), true)) {
                // do nothing as both dates are same
            } else {
                attendance_view.append("\n\n" + attendance[i].dbDate.toString())
                temp_day = attendance[i].dbDate.toString()
            }

            attendance_view.append("\nScan time: " + attendance[i].dbTime.toString() + "\tSubject: " + attendance[i].dbSub.toString())
            i = i + 1
        } while (i < table_size)


    }
}