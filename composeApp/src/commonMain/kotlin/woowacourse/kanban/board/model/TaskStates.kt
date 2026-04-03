package woowacourse.kanban.board.model

// 일급 객체
class TaskStates(val taskStates: List<TaskState>) {

    fun deleteTask(taskState: TaskState): List<TaskState> {
        if (taskState.taskStatus !in deletableTaskStates) return taskStates
        return taskStates.minus(taskState)
    }

    companion object {
        private val deletableTaskStates: List<TaskStatus> = listOf(TaskStatus.TODO, TaskStatus.IN_PROGRESS, TaskStatus.DONE)
    }
}
