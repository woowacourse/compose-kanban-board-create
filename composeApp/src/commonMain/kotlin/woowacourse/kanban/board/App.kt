package woowacourse.kanban.board

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import woowacourse.kanban.board.kanbanboard.ui.KanbanBoard
import woowacourse.kanban.board.createtaskcard.domain.TaskCardDataInputState
import woowacourse.kanban.board.kanbanboard.domain.TaskCardTable

@Composable
fun App() {
    val state = remember { TaskCardDataInputState() }
    val taskCardTable = remember { TaskCardTable() }

    KanbanBoard(
        state,
        taskCardTable
    )
}
