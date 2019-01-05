package pl.perski.lukasz.maraton.ui.act.intro

import android.content.Context
import com.chyrta.onboarder.OnboarderPage
import android.os.Bundle
import com.chyrta.onboarder.OnboarderActivity
import pl.perski.lukasz.maraton.R

class IntroActivity : OnboarderActivity(), IntroActivityMVP.View {
    val presenter = IntroActivityPresenter()

    private lateinit var onboarderPages: MutableList<OnboarderPage>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.setView(this)
        onboarderPages = ArrayList()
        setSkipButtonHidden()
        setFinishButtonTitle(resources.getString(R.string.grant))


        val onboarderPage1 = OnboarderPage(resources.getString(R.string.welcome), resources.getString(R.string.permission_needed),R.drawable.logo_lp)
        val onboarderPage2 = OnboarderPage("", resources.getString(R.string.persmission_to_voice) + resources.getString(R.string.persmission_to_storage), R.drawable.logo_lp)

        // You can define title and description colors (by default white)
//        onboarderPage1.setTitleColor(R.color.black)
//        onboarderPage1.setDescriptionColor(R.color.white)

        // Don't forget to set background color for your page
       // onboarderPage1.setBackgroundColor(R.color.LightGrey)

        // Add your pages to the list
        onboarderPages.add(onboarderPage1)
        onboarderPages.add(onboarderPage2)

        // And pass your pages to 'setOnboardPagesReady' method
        setOnboardPagesReady(onboarderPages)

    }

    public override fun onSkipButtonPressed() {
        // Optional: by default it skips onboarder to the end
        super.onSkipButtonPressed()
        // Define your actions when the user press 'Skip' button
    }

    override fun onFinishButtonPressed() {
      presenter.grantPermissions()
        // Define your actions when the user press 'Finish' button
    }


    override fun getContext(): Context {
        return this
    }

    override fun finishActivity() {
        finish()
    }


}