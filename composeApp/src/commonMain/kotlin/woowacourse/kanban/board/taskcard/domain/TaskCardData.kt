package woowacourse.kanban.board.taskcard.domain

data class TaskCardData(
    val title: String,
    val description: String? = null,
    val tags: List<String>? = null,
    val state: State,
    val manager: Manager
)
