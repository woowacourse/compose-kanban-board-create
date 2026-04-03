package woowacourse.kanban.board.model

abstract class AssignedTaskState(override val task: Task) : TaskState {
    init {
        check(task.assignee != null) { "Assignee must be set" }
    }
}
