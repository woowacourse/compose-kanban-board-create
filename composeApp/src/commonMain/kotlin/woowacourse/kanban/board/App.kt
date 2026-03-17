package woowacourse.kanban.board

import androidx.compose.runtime.Composable
import woowacourse.kanban.create.model.TaskCreateViewModel
import woowacourse.kanban.create.view.TaskCreateDialog

@Composable
fun App() {
    val state = TaskCreateViewModel()
    TaskCreateDialog(viewModel = state)
}
