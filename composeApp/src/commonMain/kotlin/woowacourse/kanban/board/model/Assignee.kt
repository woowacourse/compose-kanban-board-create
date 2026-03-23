package woowacourse.kanban.board.model

import woowacourse.kanban.board.util.ErrorMessage

data class Assignee(val name: String) {
    init {
        require(name.isNotBlank()) { ErrorMessage.ASSIGNEE_EMPTY }
    }
}
