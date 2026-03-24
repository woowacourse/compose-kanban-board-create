package woowacourse.kanban.board.model

data class TaskCard(
    val title: Title,
    val status: TaskStatus,
    val assignee: Assignee,
    val description: Description = Description.empty,
    val tags: TagGroup = TagGroup(emptyList())
)
