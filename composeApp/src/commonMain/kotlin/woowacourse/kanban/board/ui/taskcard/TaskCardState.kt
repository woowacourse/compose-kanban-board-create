package woowacourse.kanban.board.ui.taskcard

import woowacourse.kanban.board.domain.Task
import woowacourse.kanban.board.domain.TaskState

data class TaskCardState(
    val title: String,
    val state: TaskState,
    val managerName: String,
    val description: String = "",
    val tags: List<String> = emptyList(),
)

fun Task.toUiState(): TaskCardState {
    return TaskCardState(
        title = this.title,
        description = this.description,
        tags = this.tags,
        state = this.state,
        managerName = this.managerName,
    )
}
