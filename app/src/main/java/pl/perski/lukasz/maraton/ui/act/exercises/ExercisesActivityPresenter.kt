package pl.perski.lukasz.maraton.ui.act.exercises

import pl.perski.lukasz.maraton.data.model.ExerciseData

class ExercisesActivityPresenter : ExercisesActivityMVP.Presenter {

    var model = ExercisesModel()
    private lateinit var  view : ExercisesActivityMVP.View
    private lateinit var exercisesList : List<ExerciseData>

    override fun setView(view: ExercisesActivityMVP.View) {
       this.view = view
    }

    override fun getExercises(){

        exercisesList = model.getExercisesFromDB(view.getContext())
        if (exercisesList != null) {
            view.setExercises(exercisesList)
        }
    }


}