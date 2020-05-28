package pl.perski.lukasz.maraton.data.db


import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import pl.perski.lukasz.maraton.R
import spencerstudios.com.fab_toast.FabToast
import java.io.File
import java.io.FileOutputStream

object DatabaseHelper {
    private const val EXT_DATABASE_NAME = "marathon.db"
    private const val DATABASE_PATH = "marathon.db"
//    fun checkDataBase(): Boolean {
//        var checkDB = false
//        try {
//            val myPath = DATABASE_PATH + DATABASE_NAME
//            val dbfile = File(myPath)
//            checkDB = dbfile.exists()
//        } catch (e: SQLiteException) {
//
//        }
//        return checkDB
//    }

    fun copyDataBase(context: Context) {
        if (checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_GRANTED) {
            try {
                val file = File(context.getDatabasePath(DATABASE_PATH).path)
                file.parentFile.mkdirs()
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

                FabToast.makeText(context, context.resources.getString(R.string.prepare_app_success), FabToast.LENGTH_SHORT, FabToast.SUCCESS, FabToast.POSITION_DEFAULT).show()

            } catch (e: Exception) {
                Log.e("ERROR", e.toString())
                FabToast.makeText(context, context.resources.getString(R.string.prepare_app_failed), FabToast.LENGTH_SHORT, FabToast.ERROR, FabToast.POSITION_DEFAULT).show()
            }
        }
    }
}










