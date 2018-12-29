package pl.perski.lukasz.maraton.ui.act.exercises
import pl.perski.lukasz.maraton.data.enums.ExerciseGroup
import pl.perski.lukasz.maraton.data.model.ExerciseData

class ExercisesActivityPresenter : ExercisesActivityMVP.Presenter {

    var model = ExercisesModel()
    private lateinit var  view : ExercisesActivityMVP.View
    //mutable list - może być modyfikowana, zwykła lista nie może
    var groupsCodes: MutableList<Int> = ArrayList()
    var groups: MutableList<String> = ArrayList()
    var titles: MutableList<MutableList<String>> = ArrayList()

    override fun getExerciseTitlesGroup(groupId: Int): MutableList<String> {
        return model.getExerciseTitlesGroupFromDB(view.getContext(), groupId)!!
    }

    override fun setExpandableLV() {
        getExercises()
        groupsCodes.forEach{
           groups.add(ExerciseGroup.getByCode(it))
        }
        view.setExercises(groups, titles)
    }

    override fun getExercisesGroup(groupId: Int): MutableList<ExerciseData> {
return model.getExercisesGroupFromDB(view.getContext(), groupId)!!
    }

    override fun getExercisesGroupId() :MutableList<Int> {
        //TODO: tak niezbyt tutaj z tym not-null...
        return model.getExercisesGroupIdFromDB(view.getContext())!!
    }

    override fun setView(view: ExercisesActivityMVP.View) {
       this.view = view
    }

    override fun getExercises(){
   groupsCodes = getExercisesGroupId()
        groupsCodes.forEach{ it ->
            titles.add(getExerciseTitlesGroup(it))
        }
    }
}

