package woowacourse.kanban.board.model

import woowacourse.kanban.board.model.modal.Description
import woowacourse.kanban.board.model.modal.Tags
import woowacourse.kanban.board.model.modal.Title

data class TaskCardData(
    val title: Title,
    val description: Description,
    val tags: Tags,
    val task: TaskState,
    val profile: ProfileState
)
