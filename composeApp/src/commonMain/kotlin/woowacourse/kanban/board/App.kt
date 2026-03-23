package woowacourse.kanban.board

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.kanban.board.component.taskboard.KanbanBoard

@Composable
@Preview(
    showBackground = true,
    widthDp = 1300,
    heightDp = 910,
)
fun App() {
    MaterialTheme {
        KanbanBoard()
    }
}
