package pl.perski.lukasz.marathon.ui.act.main

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

class MainActivityPresenter : MainActivityMVP.Presenter {

    var model = MainModel()
    private lateinit var  view : MainActivityMVP.View
    private val  MY_PERMISSIONS_REQUEST = 322
    private lateinit var _context : Context


//    override fun onFirstLunch() {
//        //TODO: sprawdzanie booleana 'firstlunch' z sharedpref
//      getPermissions()
//        model.copyDB(_context)
//    }

    override fun setView(view: MainActivityMVP.View) {
        this.view = view
        _context = view.getContext()
    }

   override fun grantPermissions()
    {
        if (ContextCompat.checkSelfPermission
                (_context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(_context as Activity,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //TODO: O co chodzi w tym ifie? Jakie wyjaśnienie
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(_context as Activity,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
                        MY_PERMISSIONS_REQUEST)
            }
        }
    }

    override fun copyDB()
    { model.copyDB(_context)}


}