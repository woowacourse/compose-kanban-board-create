package woowacourse.kanban.board.ui.card

import woowacourse.kanban.board.domain.CardData
import woowacourse.kanban.board.domain.TaskState

data class CardState(
    val title: String,
    val state: TaskState,
    val managerName: String,
    val description: String = "",
    val tags: List<String> = emptyList(),
)

fun CardData.toUiState(): CardState {
    return CardState(
        title = this.title,
        description = this.description,
        tags = this.tags,
        state = this.state,
        managerName = this.managerName,
    )
}
