package woowacourse.kanban.board.data

data class Task(
    val taskTitle: Title,
    val taskScript: Script = Script(""),
    val tags: Tags = Tags(emptyList()),
    val status: TaskStatus = TaskStatus.TO_DO,
    val nickname: Nickname,
)
