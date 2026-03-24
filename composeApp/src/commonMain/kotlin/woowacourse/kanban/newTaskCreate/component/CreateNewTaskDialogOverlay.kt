package woowacourse.kanban.newTaskCreate.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import woowacourse.kanban.newTaskCreate.data.Assignee
import woowacourse.kanban.newTaskCreate.data.Task

@Composable
fun CreateNewTaskDialogOverlay(
    assignees: List<Assignee>,
    onDismiss: () -> Unit,
    onCreateTask: (Task) -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        CreateNewTaskDialog(
            assignees = assignees,
            onDismiss = onDismiss,
            onCreateTask = onCreateTask,
        )
    }
}

@Preview(showBackground = true, widthDp = 1294, heightDp = 1000)
@Composable
private fun CreateNewTaskDialogOverlayPreivew() {
    val assignees = listOf(
        Assignee(id = "dino", nickname = "다이노"),
        Assignee(id = "fames", nickname = "페임스"),
    )
    CreateNewTaskDialogOverlay(
        assignees = assignees,
        onDismiss = {},
        onCreateTask = {},
    )
}
