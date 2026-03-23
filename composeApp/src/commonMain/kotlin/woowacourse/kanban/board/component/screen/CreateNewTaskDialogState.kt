package woowacourse.kanban.board.component.screen

import woowacourse.kanban.board.data.Tags.Companion.validateTagsAndWordCount
import woowacourse.kanban.board.data.TaskStatus
import woowacourse.kanban.board.data.Title.Companion.validateTitle

data class CreateNewTaskDialogState(
    val title: String = "",
    val description: String = "",
    val tags: String = "",
    val selectedStatusIndex: Int = 0,
    val selectedProfileIndex: Int = 0,
    val statusOptions: List<TaskStatus> = listOf(TaskStatus.TO_DO, TaskStatus.IN_PROGRESS, TaskStatus.DONE),
    val profileOptions: List<String> = listOf("다이노", "페임스"),
) {
    val isCreateEnabled: Boolean
        get() = validateTitle(title) == null &&
                validateTagsAndWordCount(tags) == null
}
