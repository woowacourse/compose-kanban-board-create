package woowacourse.kanban.board.ui.util

import woowacourse.kanban.board.domain.TaskState

fun TaskState.getTaskStateLabel(): String {
    return when (this) {
        TaskState.TO_DO -> "To Do"
        TaskState.IN_PROGRESS -> "In Progress"
        TaskState.DONE -> "Done"
    }
}
