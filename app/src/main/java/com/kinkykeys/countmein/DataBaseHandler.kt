package com.kinkykeys.countmein

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASENAME = "MY DATABASE"

val ATTABLE = "studentAttendance"
val COL_DATE = "date"
val COL_TIME = "time"
val COL_SUB = "subject"
val COL_SUID = "stdID"

val CREDTABLE = "studentCred"
val COL_UID = "username"
val COL_PASSWD = "assword"

val FTABLE = "teacherCred"
val COL_FID = "username"
val COL_ASSWD = "assword"


class DataBaseHandler(var context:Context) : SQLiteOpenHelper(context, DATABASENAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createATTable = "CREATE TABLE " + ATTABLE + " (" + COL_DATE + " VARCHAR(256)," + COL_TIME + " VARCHAR(256)," + COL_SUB + " VARCHAR(256)," + COL_SUID + " VARCHAR(256)" + " )"
        val createCRTable = "CREATE TABLE " + CREDTABLE + " (" + COL_UID + " VARCHAR(256)," + COL_PASSWD + " VARCHAR(256)" + " )"
        val createFTable = "CREATE TABLE " + FTABLE + " (" + COL_FID + " VARCHAR(256)," + COL_ASSWD + " VARCHAR(256)" + " )"
        db?.execSQL(createATTable)
        db?.execSQL(createCRTable)
        db?.execSQL(createFTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //onCreate(db);
    }

    fun insertData(user: User) {

        val database = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COL_DATE, user.dbDate)
        contentValues.put(COL_TIME, user.dbTime)
        contentValues.put(COL_SUB, user.dbSub)
        contentValues.put(COL_SUID, user.dbUID)

        val result = database.insert(ATTABLE, null, contentValues)

        if (result == (0).toLong()) {

            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()

        } else {

            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()

        }

    }


    fun readData(): MutableList<User> {

        val list: MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $ATTABLE"
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {

                val user = User()
                user.dbDate = result.getString(result.getColumnIndex(COL_DATE))
                user.dbTime = result.getString(result.getColumnIndex(COL_TIME))
                user.dbSub = result.getString(result.getColumnIndex(COL_SUB))
                user.dbUID = result.getString(result.getColumnIndex(COL_SUID))

                list.add(user)

            } while (result.moveToNext())
        }

        return list
    }

    fun clearCreds() {

        val database = this.writableDatabase
        val contentValues = ContentValues()

        val result = database.delete(CREDTABLE, "", null)

    }

    fun clearTCreds() {

        val database = this.writableDatabase
        val contentValues = ContentValues()

        val result = database.delete(FTABLE, "", null)

    }

    fun insertCred(user: User) {

        val database = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COL_UID, user.dbUID)
        contentValues.put(COL_PASSWD, user.dbPasswd)

        val result = database.insert(CREDTABLE, null, contentValues)

        if (result == (0).toLong()) {

            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()

        } else {

            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()

        }

    }


    fun readCred(): MutableList<User> {

        val list: MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $CREDTABLE"
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {

                val user = User()
                user.dbUID = result.getString(result.getColumnIndex(COL_UID))
                user.dbPasswd = result.getString(result.getColumnIndex(COL_PASSWD))

                list.add(user)

            } while (result.moveToNext())
        }

        return list
    }




    fun insertTCred(user: Teacher) {

        val database = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COL_FID, user.dbUID)
        contentValues.put(COL_ASSWD, user.dbPasswd)

        val result = database.insert(FTABLE, null, contentValues)

        if (result == (0).toLong()) {

            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()

        } else {

            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()

        }

    }


    fun readTCred(): MutableList<Teacher> {

        val list: MutableList<Teacher> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $FTABLE"
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {

                val user = Teacher()
                user.dbUID = result.getString(result.getColumnIndex(COL_FID))
                user.dbPasswd = result.getString(result.getColumnIndex(COL_ASSWD))

                list.add(user)

            } while (result.moveToNext())
        }

        return list
    }

}