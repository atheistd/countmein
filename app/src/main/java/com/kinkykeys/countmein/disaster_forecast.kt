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

        for (i in 0 until attendance.size) {
            attendance_view.append("\nScan date: " + attendance[i].dbDate.toString() + "\tScan time: " + attendance[i].dbTime.toString() + "\tSubject: " + attendance[i].dbSub.toString())
        }

        // TO DO
        // THIS HAS NOT BEEN IMPLEMENTED YET
        // DONT FORGET
        // add the logic to add a carriage return after every day


    }
}