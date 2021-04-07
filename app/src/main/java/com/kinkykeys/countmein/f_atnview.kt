package com.kinkykeys.countmein

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class f_atnview : AppCompatActivity() {

    // new code, who dis?
    public val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_f_atnview)

        // SQLite related init
        val context = this
        val db = DataBaseHandler(context)

        val attendance_view = findViewById(R.id.teach_attn_view) as TextView

        // retrieve values
        var attendance = db.readData()
        attendance.sortBy { it.dbSub }
        attendance_view.text = ""

        var temp_day = ""
        var temp_sub = ""
        var temp_total_idiots = 0
        var chk = 0
        var table_size = attendance.size
        var i = 0

        if (table_size == 0) {
            attendance_view.append("No data in the table. Please scan the barcode to populate the attendance table.")
        } else {

            do {

                if (temp_day.equals(attendance[i].dbDate.toString(), true)) {
                    // do nothing as both dates are same
                } else {
                    attendance_view.append("\n\n" + attendance[i].dbDate.toString())
                    temp_day = attendance[i].dbDate.toString()
                }

                if (temp_sub.equals(attendance[i].dbSub.toString(), true)) {
                    // do nothing as the subject is same
                } else {
                    if ((temp_total_idiots.equals(0))) {
                        // do nothing
                    } else {
                        attendance_view.append("\nTotal:\t" + temp_total_idiots)
                        temp_sub = attendance[i].dbSub.toString()
                    }
                    attendance_view.append("\n\n" + attendance[i].dbSub.toString())
                    temp_sub = attendance[i].dbSub.toString()
                    temp_total_idiots = 0
                }

                attendance_view.append("\nStudent ID:\t" + attendance[i].dbUID.toString() + "\tat: " + attendance[i].dbTime.toString())
                temp_total_idiots = temp_total_idiots + 1
                i = i + 1
            } while (i < table_size)
            attendance_view.append("\nTotal:\t" + temp_total_idiots)


        }

    }
}