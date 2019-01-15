package pl.perski.lukasz.maraton.ui.act.intro

import android.Manifest
import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AlertDialog
import android.support.v7.view.ContextThemeWrapper
import com.kotlinpermissions.KotlinPermissions
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.adapters.SharedPrefHelper
import pl.perski.lukasz.maraton.ui.act.login.LoginActivity
import pl.perski.lukasz.maraton.ui.act.main.MainActivity

class IntroActivityPresenter : IntroActivityMVP.Presenter {


    private lateinit var view: IntroActivityMVP.View
    private lateinit var context: Context
    var model = IntroModel()
    lateinit var sharedPrefHelper: SharedPrefHelper

    override fun setView(view: IntroActivityMVP.View) {
        this.view = view
        context = view.getContext()
        sharedPrefHelper = SharedPrefHelper(context)
    }

    override fun grantPermissions() {
        KotlinPermissions.with(context as FragmentActivity) // where this is an FragmentActivity instance
                .permissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO
                )
                .onAccepted {
                    //List of accepted permissions
                    model.copyDB(context)
                    sharedPrefHelper.firstLaunch = false
                    view.finishActivity()
                    context.startActivity(Intent(context, LoginActivity::class.java))
                }//TODO: obsłużenie, gdy użytkownik odmówi tylko na dźwięk
                .onDenied {
                    showAlertDialog()
                }
                .onForeverDenied {
                showAlertDialog()
                }
                .ask()
    }


    fun showAlertDialog()
    {
        val builder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.DialogTheme))
        with(builder)
        {
            setTitle(context.resources.getString(R.string.permissions_explain))
            setPositiveButton(context.resources.getString(R.string.re_grant)) { dialogInterface, i ->
                run {
                    grantPermissions()
                }
            }
            setNegativeButton(context.resources.getString(R.string.close_app))
            { dialogInterface, i ->
                run {
                    view.finishActivity()
                }
            }
            show()
        }
    }
}