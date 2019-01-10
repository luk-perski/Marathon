package pl.perski.lukasz.maraton.ui.act.login

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class LoginModel : LoginActivityMVP.Model {


    val auth = FirebaseAuth.getInstance()!!

    override fun resetPassword(emailAddress: String) {
        try {


            auth.sendPasswordResetEmail(emailAddress)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("EMAIL", task.result.toString())
                        } else {
                            // Log.d("EMAIL", task.result.toString())
                        }

                    }
        } catch (e : com.google.firebase.auth.FirebaseAuthInvalidCredentialsException) {
            Log.d("EMAIL", e.message)
        }
    }
}