package woowacourse.kanban.board

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.kanban.board.ui.kanbanBoard.KanbanBoardSection
import woowacourse.kanban.board.ui.sample.TaskCardPreviewData

@Preview(showBackground = true, widthDp = 1000, heightDp = 900)
@Composable
private fun KanbanBoardPreview() {
    val taskCards = TaskCardPreviewData().values.toList()
    MaterialTheme {
        KanbanBoardSection(taskCards = taskCards)
    }
}

@Composable
fun App() {
    val taskCards = TaskCardPreviewData().values.toList()

    MaterialTheme {
        KanbanBoardSection(taskCards)
    }
}
