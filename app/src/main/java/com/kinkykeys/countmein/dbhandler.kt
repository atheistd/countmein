package com.kinkykeys.countmein

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.security.AccessControlContext

val DATABASE_NAME = "sigma"
val TABLE_NAME = "students_x"
val COL_ID = "id"
val COL_SUB1 = "sub1"
val COL_SUB2 = "sub2"
val COL_SUB3 = "sub3"
val COL_SUB4 = "sub4"

class dbhandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + "INTEGER PRIMARY KEY," +
                COL_SUB1 + "VARCHAR(256)," +
                COL_SUB2 + "VARCHAR(256)," +
                COL_SUB3 + "VARCHAR(256)," +
                COL_SUB4 + "VARCHAR(256)),";

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(studentx : student) {

        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_ID,studentx.id)
        cv.put(COL_SUB1,studentx.sub1)
        cv.put(COL_SUB2,studentx.sub2)
        cv.put(COL_SUB3,studentx.sub3)
        cv.put(COL_SUB4,studentx.sub4)

        var result = db.insert(TABLE_NAME,null,cv)

        if(result == -1.toLong())
            Toast.makeText(context, "Database update failed. Fuck off cunt.",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Done. Now study.",Toast.LENGTH_SHORT).show()

    }
}