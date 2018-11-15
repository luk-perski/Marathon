package pl.perski.lukasz.maraton.ui.act.fragmentContainer

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import pl.perski.lukasz.maraton.R

class FragmentContainerActivity : AppCompatActivity(), FragmentContainerActMVP.View {



    var presenter = FragmentContainerActPresenter(supportFragmentManager)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_container)
        presenter.setView(this)
        presenter.showFragment(intent.getStringExtra("fragment"))

    }

    override fun getContext(): Context {
        return this
    }
}
