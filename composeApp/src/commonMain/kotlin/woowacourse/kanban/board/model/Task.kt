package woowacourse.kanban.board.model

data class Task(
    val title: String,
    val description: String? = null,
    val tags: List<Tag> = emptyList(),
    val assignee: String? = null,
)
