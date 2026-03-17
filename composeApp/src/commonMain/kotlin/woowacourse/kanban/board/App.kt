package woowacourse.kanban.board

import androidx.compose.runtime.Composable
import woowacourse.kanban.create.model.TaskCreateState
import woowacourse.kanban.create.view.TaskCreateDialog

@Composable
fun App() {
    val state = TaskCreateState()
    TaskCreateDialog(state = state)
}
