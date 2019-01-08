package pl.perski.lukasz.maraton.ui.fragments.exerciseFragments


import android.os.Bundle
import android.support.v4.app.Fragment
import pl.perski.lukasz.maraton.data.model.ExerciseData
import pl.perski.lukasz.maraton.data.model.ExerciseDoneData
import java.io.Serializable

 abstract class ExerciseBaseFragment : Fragment()  {

     //TODO: companion?
     companion object {

         val EXERCISE = "exercise"
         var exercise: ExerciseData? = null
         fun newInstance(exercise: ExerciseData): ExerciseBaseFragment {
             val args = Bundle()
             var fragment: ExerciseBaseFragment? = null
             args.putSerializable(EXERCISE, exercise as Serializable)

             when (exercise.exerciseTypeId) {
                 1 -> fragment = ExerciseFragmentTypeOne()
                 2 -> fragment = ExerciseFragmentTypeTwo()
                 3 -> fragment = ExerciseFragmentTypeThree()
                 4 -> fragment = ExerciseFragmentTypeFour()
                 5 -> fragment = ExerciseFragmentTypeFive()
                 6 -> fragment = ExerciseFragmentTypeSix()
                 7 -> fragment = ExerciseFragmentTypeSeven()
                 8 -> fragment = ExerciseFragmentTypeEight()
             }
             fragment?.arguments = args
             return fragment!!
         }

         fun getDataFromArg(fragment: ExerciseBaseFragment) {
             val bundle = fragment.arguments
             if (bundle != null) {
                 exercise = bundle.getSerializable(EXERCISE) as ExerciseData?
             }
         }
     }

     abstract  fun getData(isDone : Boolean, repeatAmount : Int?, timeAmount: Int?): ExerciseDoneData
}