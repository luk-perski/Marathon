package pl.perski.lukasz.maraton.data.model

data class ExerciseDoneData(
        val recId : Int,
        val title: String,
        val exerciseGroupId : Int,
        val exerciseTypeId: Int,
        val isDone : Boolean,
        val repeatAmount : Int?,
        val timeAmount : Int?,
        val maxAmount : Int?,
        val recordPatch : String?)
{
    override fun toString(): String {
        return title
    }
}