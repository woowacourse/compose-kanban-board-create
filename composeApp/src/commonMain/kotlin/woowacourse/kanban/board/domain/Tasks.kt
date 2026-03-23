package woowacourse.kanban.board.domain

class Tasks {
    private val tasks: MutableList<Task> = mutableListOf()

    fun getAllTasks() = tasks.toList()

    fun addTask(task: Task) = tasks.add(task)

    fun countAllTasks(): Int = tasks.count()

    fun countTasksByState(state: TaskState): Int = tasks.count { it.state == state }

    fun getCompleteRate(): Float {
        val numOfAllCard = countAllTasks()
        val numOfDoneCards = countTasksByState(TaskState.DONE)

        if (numOfAllCard == 0) return 0f
        return (numOfDoneCards.toFloat() / numOfAllCard.toFloat())
    }
}
