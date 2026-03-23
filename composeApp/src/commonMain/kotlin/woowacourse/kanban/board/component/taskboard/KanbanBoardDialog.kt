package woowacourse.kanban.board.component.taskboard

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import woowacourse.kanban.board.component.taskcard.KanbanCardForm
import woowacourse.kanban.board.component.taskmodal.ModalCreateForm
import woowacourse.kanban.board.component.taskmodal.ModalCreateFormState
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.TaskState

@Composable
fun KanbanBoardDialog(
    onClickCancel: () -> Unit,
    onClickConfirm: (KanbanCardForm) -> Unit,
    modalState: ModalCreateFormState,
    assignees: List<Assignee>,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = { onClickCancel() }) {
        Card(
            modifier = modifier.height(818.dp)
                .width(672.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            ModalCreateForm(
                onClickCancel = { onClickCancel() },
                onClickConfirm = {
                    val newTask = KanbanCardForm(
                        status = TaskState.entries[modalState.status],
                        title = modalState.title,
                        content = modalState.content,
                        assignee = assignees[modalState.assignee],
                        tags = modalState.tags,
                    )
                    onClickConfirm(newTask)
                },
                modalState = modalState,
                assignees = assignees,
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 1300,
    heightDp = 910,
)
@Composable
fun KanbanBoardDialogPreview() {
    val assignees = listOf(
        Assignee("커비"),
        Assignee("바드"),
        Assignee("아오"),
    )

    KanbanBoardDialog(
        onClickCancel = { },
        onClickConfirm = { },
        modalState = ModalCreateFormState(),
        assignees = assignees,
    )
}
