package pl.perski.lukasz.maraton.ui.act.login

import android.content.Intent
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import pl.perski.lukasz.maraton.ui.act.main.MainActivity

class LoginActivityPresenter :LoginActivityMVP.Presenter {

    private lateinit var view: LoginActivityMVP.View
    private val auth = FirebaseAuth.getInstance()


    override fun setView(view: LoginActivityMVP.View) {
        this.view = view
    }

    override fun setControls() {
    }

    //TODO: Ogarnij model do tego
    override fun loginUser() {
        if (validateEntries()) {
            view.manageProgressBar(View.VISIBLE)
            auth.signInWithEmailAndPassword(view.getUserEmail(), view.getUserPassword()).addOnCompleteListener { task: Task<AuthResult> ->
                view.manageProgressBar(View.GONE)
                if (task.isSuccessful) {
                    Toast.makeText(view.getContext(), "Logowanie powiodło się.", Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(view.getContext(), "Rejestracja powiodła się.", Toast.LENGTH_SHORT).show()
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
        view.getContext().startActivity(Intent(view.getContext(), MainActivity::class.java))
        view.finishActivity()
    }

}