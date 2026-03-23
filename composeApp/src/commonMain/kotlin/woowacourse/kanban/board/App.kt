package woowacourse.kanban.board

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import woowacourse.kanban.board.ui.board.KanbanBoard
import woowacourse.kanban.board.ui.preview.KanbanPreview

@Composable
@KanbanPreview
fun App() {
    MaterialTheme {
        KanbanBoard()
    }
}
