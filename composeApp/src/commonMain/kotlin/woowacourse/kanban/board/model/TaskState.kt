package woowacourse.kanban.board.model

import woowacourse.kanban.board.ComponentText

enum class TaskState(val text: String) {
    TODO(ComponentText.STATE_BUTTON_TODO),
    PROGRESS(ComponentText.STATE_BUTTON_PROGRESS),
    DONE(ComponentText.STATE_BUTTON_DONE),
}
