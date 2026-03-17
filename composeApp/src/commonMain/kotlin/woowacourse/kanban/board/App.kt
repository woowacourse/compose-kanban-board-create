package woowacourse.kanban.board

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import woowacourse.kanban.create.model.TaskCreateViewModel
import woowacourse.kanban.create.view.TaskCreateDialog

@Composable
fun App() {
    val viewModel = TaskCreateViewModel()
    TaskCreateDialog(viewModel = viewModel, modifier = Modifier)
}
