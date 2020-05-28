package pl.perski.lukasz.maraton.ui.act.login

import android.content.Context

interface LoginActivityMVP {

    interface View {
        fun getContext(): Context
        fun setToolbarTittle(tittle: String)
        fun setEvents()
        fun changeBtnSignInState(value: Int)
        fun changeBtnSignUpState(value: Int)
        fun getUserEmail(): String
        fun getUserPassword(): String
        fun setLoginQuestion(text: String)
        fun manageProgressBar(value: Int)
        fun setEmailError()
        fun setPasswordError()
        fun finishActivity()
    }

    interface Presenter {
        fun setView(view: View)
        fun registerUser()
        fun loginUser()
        fun resetPassword()
    }

    interface Model {
        fun resetPassword(emailAddress: String)
    }
}