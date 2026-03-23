package woowacourse.kanban.board.ui.kanbanBoard

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.kanban.board.model.Status
import woowacourse.kanban.board.model.TaskCard
import woowacourse.kanban.board.ui.sample.TaskCardPreviewData

@Preview(showBackground = true)
@Composable
private fun KanbanBoardBodySectionPreview() {
    val taskCards = TaskCardPreviewData().values.toList()
    MaterialTheme {
        KanbanBoardBodySection(taskCards = taskCards)
    }
}

@Composable
fun KanbanBoardBodySection(taskCards: List<TaskCard>) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState()),
    ) {
        Status.entries.forEach { status ->
            val taskByStatus = tasksByStatus(taskCards, status)
            KanbanColumnSection(status, taskByStatus)
        }
    }
}

internal fun tasksByStatus(taskCards: List<TaskCard>, status: Status): List<TaskCard> {
    return taskCards.filter { it.status == status }
}
