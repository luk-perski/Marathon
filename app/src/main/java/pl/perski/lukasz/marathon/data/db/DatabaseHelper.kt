package pl.perski.lukasz.marathon.data.db


import android.content.ContentValues.TAG
import java.io.File
import java.io.FileOutputStream

import android.content.Context

import android.widget.Toast
import pl.perski.lukasz.marathon.R

import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase

import android.support.v4.content.PermissionChecker.checkSelfPermission
import android.util.Log



object DatabaseHelper {


    const val EXT_DATABASE_NAME = "external_DB"
   const val DATABASE_PATH = "marathon.db"
   var myDataBase: SQLiteDatabase? = null
    val TABLE_ACCOUNTS = "exercises"



//    private var myDataBase: SQLiteDatabase? = null
//
//
//    // Create a empty database on the system
//    @Throws(IOException::class)
//    fun createDataBase() {
//
//        val dbExist = checkDataBase()
//
//        if (dbExist) {
//            Log.v("DB Exists", "db exists")
//
//        } else {
//
//            this.readableDatabase
//            try {
//                this.close()
//                copyDataBase()
//            } catch (e: IOException) {
//                throw Error("Error copying database")
//            }
//        }
//
//    }

    // Check database already exist or not
//    fun checkDataBase(): Boolean {
//        var checkDB = false
//        try {
//            val myPath = DATABASE_PATH + DATABASE_NAME
//            val dbfile = File(myPath)
//            checkDB = dbfile.exists()
//        } catch (e: SQLiteException) {
//            println("Database doesn't exists.")
//        }
//        return checkDB
//    }

    // Copies your database from your local assets-folder to the just created
    // empty database in the system folder

    fun copyDataBase(context: Context) {





        if (checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {


            val file = File(context.getDatabasePath(DATABASE_PATH).path)
          //  val file = File(Environment.getExternalStorageDirectory().toString() + context.resources.getString(R.string.app_name))
            file.parentFile.mkdirs() // Will create parent directories if not exists
            file.createNewFile()

            val myOutput = FileOutputStream(file)
            val myInput = context.assets.open(EXT_DATABASE_NAME)

            val buffer = ByteArray(1024)
            var length: Int = myInput.read(buffer)
            while ((length) > 0) {
                myOutput.write(buffer, 0, length)
                length = myInput.read(buffer)
            }
            myInput.close()
            myOutput.flush()
            myOutput.close()
            Toast.makeText(context, "Database is now avalible!", Toast.LENGTH_LONG).show()


//        } else {
//            Toast.makeText(context, "Database doesn't exists!", Toast.LENGTH_LONG).show()
//        }
        }
    }


//    // Open database
//    fun openDatabase(context: Context) {
//        val myPath = context.getDatabasePath(DatabaseHelper.DATABASE_PATH).path
//        myDataBase = SQLiteDatabase.openDatabase(myPath, null,
//                SQLiteDatabase.OPEN_READWRITE)
//    }
//
//
//    fun closeDataBase() {
//        if (myDataBase != null)
//            myDataBase!!.close()
//    }
////
////    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
////        if (newVersion > oldVersion) {
////            Log.v("Database Upgrade", "Database version higher than old.")
////            db_delete()
////        }
////    }
////
////    override fun onCreate(db: SQLiteDatabase) {
////        // TODO Auto-generated method stub
////    }
//
//    fun userSignup(email: String, password: String): Boolean
//    {
//        val db = this.getReadableDatabase()
//        val c = db.rawQuery("SELECT column1,column2,column3 FROM table ", null)
//        if (c.moveToFirst()) {
//            do {
//                // Passing values
//                val column1 = c.getString(0)
//                val column2 = c.getString(1)
//                val column3 = c.getString(2)
//                // Do something Here with values
//            } while (c.moveToNext())
//        }
//    }
//
////    fun userLogin(email: String , password: String) : Boolean
////    {
////        var ret = "select * from ${TABLE_ACCOUNTS} where email = '"+email+"' AND password = '"+password+"'"
////        var cursor: Cursor = myDataBase!!.rawQuery(ret, null)
////        if(cursor.count == 1)
////        {
////            return true
////        }
////        else
////        {
////            return false
////        }
////    }

}










