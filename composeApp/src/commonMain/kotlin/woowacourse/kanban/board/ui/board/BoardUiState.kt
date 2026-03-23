package woowacourse.kanban.board.ui.board

import woowacourse.kanban.board.ui.taskcard.TaskCardUiState

data class BoardUiState(
    val showCardForm: Boolean,
    val completeRate: Float,
    val countOfDoneTasks: Int,
    val countOfAllTasks: Int,
    val toDoTaskCards: List<TaskCardUiState>,
    val inProgressTaskCards: List<TaskCardUiState>,
    val doneTaskCards: List<TaskCardUiState>
)
