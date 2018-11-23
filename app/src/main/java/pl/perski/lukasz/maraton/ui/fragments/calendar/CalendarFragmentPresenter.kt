package pl.perski.lukasz.maraton.ui.fragments.calendar

class CalendarFragmentPresenter : CalendarFragmentMVP.Presenter {


    private lateinit var view: CalendarFragmentMVP.View

    override fun setView(view: CalendarFragmentMVP.View) {
        this.view = view
    }

    override fun setControls() {
        //TODO: zmień na wartości z R.
        view.setToolbarTittle("Maraton", "Kalendarz")
    }
}