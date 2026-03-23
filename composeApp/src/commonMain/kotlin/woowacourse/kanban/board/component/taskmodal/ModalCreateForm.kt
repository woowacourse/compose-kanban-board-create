package woowacourse.kanban.board.component.taskmodal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import woowacourse.kanban.board.component.taskcard.KanbanCardForm
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.TaskState

@Composable
fun ModalCreateForm(
    assignees: List<Assignee>,
    modifier: Modifier = Modifier,
    onClickCancel: () -> Unit,
    onClickConfirm: (KanbanCardForm) -> Unit,
    modalState: ModalCreateFormState,
) {

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        ModalHeader(onClickCancel = { onClickCancel() })

        HorizontalDivider(
            thickness = Dp.Hairline,
            color = Color.LightGray,
        )

        ModalBody(
            onClickCancel = { onClickCancel() },
            assignees = assignees,
            onClickConfirm = {
                val newTask = KanbanCardForm(
                    title = modalState.title,
                    content = modalState.content,
                    tags = modalState.tags,
                    status = TaskState.entries[modalState.status],
                    assignee = assignees[modalState.assignee],
                )
                onClickConfirm(newTask)
            },
            modalState = modalState,
            modifier = Modifier,
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 672,
    heightDp = 1000,
)
@Composable
private fun ModalCreateFormPreview() {
    val state = remember { ModalCreateFormState() }

    val assignees = listOf(
        Assignee("커비"),
        Assignee("바드"),
        Assignee("아오"),
    )

    ModalCreateForm(
        onClickCancel = {},
        onClickConfirm = {},
        modalState = state,
        assignees = assignees,
    )
}
