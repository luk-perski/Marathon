package pl.perski.lukasz.maraton.ui.act.login

import android.content.Context
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.view.ContextThemeWrapper
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import pl.perski.lukasz.maraton.R
import pl.perski.lukasz.maraton.adapters.SharedPrefHelper
import pl.perski.lukasz.maraton.ui.act.intro.IntroActivity
import pl.perski.lukasz.maraton.ui.act.main.MainActivity
import spencerstudios.com.fab_toast.FabToast

class LoginActivityPresenter :LoginActivityMVP.Presenter {


    private lateinit var view: LoginActivityMVP.View
    private val auth = FirebaseAuth.getInstance()
    private lateinit var context: Context
    var model = LoginModel()
    lateinit var sharedPrefHelper : SharedPrefHelper

    override fun setView(view: LoginActivityMVP.View) {
        this.view = view
        context = view.getContext()
        sharedPrefHelper = SharedPrefHelper(context)
    }

    override fun resetPassword() {
        val builder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.DialogTheme))
        val inflater = LayoutInflater.from(context)
        builder.setTitle(context.resources.getString(R.string.reset_password))
        builder.setMessage(context.resources.getString(R.string.reset_password_email_question))
        val dialogLayout = inflater.inflate(R.layout.alert_dialog_edit_text, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.editText)
        editText.hint = context.resources.getString(R.string.enter_email)
        editText.inputType = EditorInfo.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        builder.setView(dialogLayout)
        builder.setNegativeButton(R.string.cancel, null)
        builder.setPositiveButton(R.string.send) { dialogInterface, i ->

            val emailAddress = editText.text.toString()
            if (emailAddress.isEmpty() or !Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
                resetPassword()
                //TODO: zmień toast na alertdialog
                FabToast.makeText(context, context.resources.getString(R.string.error_invalid_email),
                        FabToast.LENGTH_LONG, FabToast.ERROR, FabToast.POSITION_CENTER).show()
            } else {
                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                FabToast.makeText(context, context.resources.getString(R.string.email_send),
                                        FabToast.LENGTH_LONG, FabToast.SUCCESS, FabToast.POSITION_CENTER).show()
                            } else {
                                //TODO: zmień toast na alertdialog
                                FabToast.makeText(context, task.exception?.message,
                                        FabToast.LENGTH_LONG, FabToast.ERROR, FabToast.POSITION_CENTER).show()
                            }
                        }
            }
        }
        builder.show()
    }

    override fun loginUser() {
        if (validateEntries()) {
            view.manageProgressBar(View.VISIBLE)
            auth.signInWithEmailAndPassword(view.getUserEmail(), view.getUserPassword()).addOnCompleteListener { task: Task<AuthResult> ->
                view.manageProgressBar(View.GONE)
                if (task.isSuccessful) {
                    FabToast.makeText(context, context.resources.getString(R.string.login_successful),
                            FabToast.LENGTH_LONG, FabToast.INFORMATION, FabToast.POSITION_DEFAULT).show()
                    showMainActivity()

                } else {
                    Toast.makeText(view.getContext(), task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun registerUser() {
        if (validateEntries()) {
            view.manageProgressBar(View.VISIBLE)
            auth.createUserWithEmailAndPassword(view.getUserEmail(), view.getUserPassword()).addOnCompleteListener { task: Task<AuthResult> ->
                view.manageProgressBar(View.GONE)
                if (task.isSuccessful) {
                    Toast.makeText(view.getContext(), R.string.register_successful,Toast.LENGTH_SHORT).show()
                    showMainActivity()
                } else {
                    //przechwycenie wyjątku
                    //if (task.exception is FirebaseAuthUserCollisionException ) {}
                    Toast.makeText(view.getContext(), task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validateEntries(): Boolean {
        if (view.getUserEmail().isEmpty() or !Patterns.EMAIL_ADDRESS.matcher(view.getUserEmail()).matches()) {
            view.setEmailError()
            return false
        } else if (view.getUserPassword().isEmpty() or (view.getUserPassword().length < 6)) {
            view.setPasswordError()
            return false
        }
        return true
    }

    private fun showMainActivity() {
        if (sharedPrefHelper.firstLaunch) {
            context.startActivity(Intent(context, IntroActivity::class.java))
        }
        else{
        context.startActivity(Intent(context, MainActivity::class.java))
        }
        view.finishActivity()
    }

}