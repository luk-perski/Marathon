package pl.perski.lukasz.maraton.data.model

data class ExerciseDoneData(
        val recId : Int = -1,
        val title: String ="",
        val exerciseGroupId : Int = -1,
        val exerciseTypeId: Int = -1,
        val isDone : Boolean = false,
        val repeatAmount : Int? = null,
        val timeAmount : Int? = null,
        val maxAmount : Int? = null,
        val recordPatch : String? = null)
{
    override fun toString(): String {
        return title
    }
}