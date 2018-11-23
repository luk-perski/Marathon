package pl.perski.lukasz.maraton.ui.act.login

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import kotlinx.android.synthetic.main.activity_login.*
import pl.perski.lukasz.maraton.R

class LoginActivity : AppCompatActivity(), LoginActivityMVP.View {

    var presenter = LoginActivityPresenter()
    private val buttonClick = AlphaAnimation(1f, 0.8f)

    override fun finishActivity() {
        finish()
    }

    override fun setEmailError() {
        etEmail.error = "Nieprawiodłowy mail"
        etEmail.requestFocus()
    }

    override fun setPasswordError() {
    etPassword.error = "Hasło powinno zawierać min. 6 znaków"
        etPassword.requestFocus()
    }

    override fun changeBtnSignInState(value: Int) {
        btnSignIn.visibility = value
    }

    override fun changeBtnSignUpState(value: Int) {
        btnSignUp.visibility = value
    }

    override fun setLoginQuestion(text: String) {
        tvLoginQuestion.text = text
    }

    override fun manageProgressBar(value: Int) {
        progressbar.visibility = value
    }

    override fun getUserEmail(): String {
return etEmail.text.toString().trim()
    }

    override fun getUserPassword(): String {
        return etPassword.text.toString().trim()     }


    override fun getContext(): Context {
        return this
    }

    override fun setToolbarTittle(tittle : String) {
        supportActionBar?.title = getString(R.string.signIn)
        //supportActionBar?.subtitle = subtitle
    }

    override fun setEvents() {
        btnSignUp.setOnClickListener {
            btnSignUp.startAnimation(buttonClick)
            presenter.registerUser()
        }
        btnSignIn.setOnClickListener {
            btnSignIn.startAnimation(buttonClick)
            presenter.loginUser()
        }
        tvLoginQuestion.setOnClickListener {
            if (tvLoginQuestion.text == this.getString(R.string.changeToSingUp)) {
                setToolbarTittle(getString(R.string.signUp))
                btnSignIn.visibility = View.INVISIBLE
                btnSignUp.visibility = View.VISIBLE
                tvLoginQuestion.text = this.getString(R.string.changeToSingIn)
            } else {
                setToolbarTittle(getString(R.string.signIn))
                btnSignIn.visibility = View.VISIBLE
                btnSignUp.visibility = View.INVISIBLE
                tvLoginQuestion.text = this.getString(R.string.changeToSingUp)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.setView(this)
        setEvents()
    }
}
