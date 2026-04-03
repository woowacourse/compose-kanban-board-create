package woowacourse.kanban.board.model


// open class
// interface
// abstract class

// 중복을 개선하여 얻는 이점이 무엇이냐?
// 상속 vs 조합 -> is-a관계 vs has-a관계

interface TaskState {
    val task: Task
    val taskStatus: TaskStatus
    fun moveTo(taskStatus: TaskStatus): TaskState
}

class Todo(override val task: Task) : TaskState {
    override val taskStatus: TaskStatus = TaskStatus.TODO
    override fun moveTo(taskStatus: TaskStatus): TaskState {
        return when (taskStatus) {
            TaskStatus.TODO -> this
            TaskStatus.IN_PROGRESS -> InProgress(task)
            TaskStatus.REVIEW, TaskStatus.DONE -> throw IllegalStateException()
        }
    }
}

class InProgress(task: Task) : AssignedTaskState(task) {
    override val taskStatus: TaskStatus = TaskStatus.IN_PROGRESS
    override fun moveTo(taskStatus: TaskStatus): TaskState {
        return when (taskStatus) {
            TaskStatus.IN_PROGRESS -> this
            TaskStatus.TODO -> Todo(task)
            TaskStatus.REVIEW -> Review(task)
            TaskStatus.DONE -> throw IllegalStateException()
        }
    }
}

class Review(task: Task) : AssignedTaskState(task) {
    override val taskStatus: TaskStatus = TaskStatus.REVIEW

    override fun moveTo(taskStatus: TaskStatus): TaskState {
        return when (taskStatus) {
            TaskStatus.REVIEW -> this
            TaskStatus.DONE -> Done(task)
            TaskStatus.IN_PROGRESS -> InProgress(task)
            else -> throw IllegalStateException()
        }
    }
}

class Done(task: Task) : AssignedTaskState(task) {
    override val taskStatus: TaskStatus = TaskStatus.DONE

    override fun moveTo(taskStatus: TaskStatus): TaskState {
        return when (taskStatus) {
            TaskStatus.DONE -> this
            TaskStatus.TODO -> Todo(task)
            TaskStatus.IN_PROGRESS, TaskStatus.REVIEW -> throw IllegalStateException()
        }
    }
}
