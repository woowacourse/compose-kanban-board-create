package woowacourse.kanban.board.ui

import woowacourse.kanban.board.domain.CardData
import woowacourse.kanban.board.domain.TaskState

data class CardUiState(
    val title: String,
    val description: String,
    val tags: List<String>,
    val state: TaskState,
    val managerName: String
)

fun CardData.toCardUiState(): CardUiState {
    return CardUiState(
        title = this.title,
        description = this.description,
        tags = this.tags,
        state = this.state,
        managerName = this.managerName
    )
}
