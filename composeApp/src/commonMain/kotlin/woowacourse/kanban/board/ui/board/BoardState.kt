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
import woowacourse.kanban.board.ui.taskcard.TaskCardUiState
import woowacourse.kanban.board.ui.taskcard.toUiState

class BoardState(private val tasks: Tasks = Tasks()) {
    var showCardCreationPanel by mutableStateOf(false)
    val snackbarHostState = SnackbarHostState()
    val uiTaskCards = mutableStateListOf<TaskCardUiState>().apply {
        addAll(tasks.getAllTasks().map { it.toUiState() })
    }
    var completeRate by mutableFloatStateOf(tasks.getCompleteRate())
    var countOfDoneTasks by mutableIntStateOf(tasks.countTasksByState(TaskState.DONE))
    var countOfAllTasks by mutableIntStateOf(tasks.countAllTasks())

    fun createTaskCard(task: Task) {
        tasks.addTask(task)
        refreshUiCards()
    }

    fun cardsByState(state: TaskState): List<TaskCardUiState> {
        return uiTaskCards.filter { it.state == state }
    }

    private fun refreshUiCards() {
        val newUiCards = tasks.getAllTasks().map { it.toUiState() }
        uiTaskCards.clear()
        uiTaskCards.addAll(newUiCards)

        completeRate = tasks.getCompleteRate()
        countOfDoneTasks = tasks.countTasksByState(TaskState.DONE)
        countOfAllTasks = tasks.countAllTasks()

    }
}
