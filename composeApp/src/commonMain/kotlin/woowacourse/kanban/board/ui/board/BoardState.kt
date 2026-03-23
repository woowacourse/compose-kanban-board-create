package woowacourse.kanban.board.ui.board

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.kanban.board.domain.Tasks
import woowacourse.kanban.board.domain.Task
import woowacourse.kanban.board.domain.TaskState
import woowacourse.kanban.board.ui.taskcard.TaskCardState
import woowacourse.kanban.board.ui.taskcard.toUiState

class BoardState(private val boardData: Tasks = Tasks()) {
    var showCardCreationPanel by mutableStateOf(false)
    val snackbarHostState = SnackbarHostState()
    val uiCards = mutableStateListOf<TaskCardState>().apply {
        addAll(boardData.getAllTasks().map { it.toUiState() })
    }
    var completeRate by mutableFloatStateOf(boardData.getCompleteRate())
    var countOfDoneCards by mutableIntStateOf(boardData.countTasksByState(TaskState.DONE))
    var countOfAllCard by mutableIntStateOf(boardData.countAllTasks())

    fun createCard(task: Task) {
        boardData.addTask(task)
        refreshUiCards()
    }

    fun cardsByState(state: TaskState): List<TaskCardState> {
        return uiCards.filter { it.state == state }
    }

    private fun refreshUiCards() {
        val newUiCards = boardData.getAllTasks().map { it.toUiState() }
        uiCards.clear()
        uiCards.addAll(newUiCards)

        completeRate = boardData.getCompleteRate()
        countOfDoneCards = boardData.countTasksByState(TaskState.DONE)
        countOfAllCard = boardData.countAllTasks()

    }
}
