package pl.perski.lukasz.maraton.ui.act.intro

import android.Manifest
import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity
import com.kotlinpermissions.KotlinPermissions
import pl.perski.lukasz.maraton.adapters.SharedPrefHelper
import pl.perski.lukasz.maraton.ui.act.main.MainActivity

class IntroActivityPresenter : IntroActivityMVP.Presenter {

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
                    context.startActivity(Intent(context, MainActivity::class.java))
                    sharedPrefHelper.firstLaunch = false
                    view.finishActivity()
                }
                .onDenied {
                    //List of denied permissions
                }
                .onForeverDenied {
                    //List of forever denied permissions
                }
                .ask()
    }

    private lateinit var view: IntroActivityMVP.View
    private lateinit var context: Context
    var model = IntroModel()
    lateinit var sharedPrefHelper: SharedPrefHelper

    override fun setView(view: IntroActivityMVP.View) {
        this.view = view
        context = view.getContext()
        sharedPrefHelper = SharedPrefHelper(context)
    }
}