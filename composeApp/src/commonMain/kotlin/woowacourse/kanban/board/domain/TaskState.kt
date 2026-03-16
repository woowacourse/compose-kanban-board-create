package woowacourse.kanban.board.domain

enum class TaskState(val label: String) {
    TO_DO("To Do"),
    IN_PROGRESS("In Progress"),
    DONE("Done")
}
