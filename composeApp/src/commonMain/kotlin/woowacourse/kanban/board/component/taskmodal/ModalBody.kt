package woowacourse.kanban.board.component.taskmodal

import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.component.taskcard.KanbanCardForm
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.TagError
import woowacourse.kanban.board.model.TaskState
import woowacourse.kanban.board.model.TitleError

@Composable
fun ModalBody(
    modalState: ModalCreateFormState,
    assignees: List<Assignee>,
    onClickCancel: () -> Unit,
    onClickConfirm: (KanbanCardForm) -> Unit,
    modifier: Modifier = Modifier,
) {
    val tagSupportMessage = when (modalState.tagError) {
        TagError.TAG_FORM_INVALID -> "태그 형식이 올바르지 않습니다."
        TagError.TAG_OVER_N -> "태그는 5자 이내로 5개까지만 등록할 수 있습니다."
        null -> "5자 이내의 태그를 최대 5개까지 등록할 수 있습니다."
    }

    val titleSupportMessage = when (modalState.titleError) {
        TitleError.TITLE_FORM_INVALID -> "제목을 입력해주세요"
        null -> ""
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 24.dp),
    ) {
        ModalBodyInput(
            title = "제목",
            essential = true,
            placeHolder = "태스크 제목을 입력하세요",
            maxLines = 1,
            supportingText = titleSupportMessage,
            onValueChange = {
                modalState.title = it
            },
            isValid = modalState.titleError == null,
            state = modalState.title,
        )

        ModalBodyInput(
            title = "설명",
            essential = false,
            placeHolder = "태스크에 대한 자세한 설명을 입력하세요",
            maxLines = 5,
            supportingText = "",
            state = modalState.content,
            isValid = true,
            onValueChange = {
                modalState.content = it
            },
        )
        ModalBodyInput(
            title = "태그",
            essential = false,
            placeHolder = "태그를 쉼표로 구분하여 입력하세요 (예: 버그, 긴급)",
            maxLines = 1,
            supportingText = tagSupportMessage,
            state = modalState.tag,
            isValid = modalState.tagError == null,
            onValueChange = {
                modalState.tag = it
            },
        )

        ModalBodySelector(
            title = "상태",
            essential = true,
            items = TaskState.entries.map { state ->
                when (state) {
                    TaskState.TODO -> "To Do"
                    TaskState.IN_PROGRESS -> "In Progress"
                    TaskState.DONE -> "Done"
                }
            },
            content = @Composable { name, id ->
                ModalOptionButton(
                    onClick = { modalState.status = id },
                    isSelected = modalState.status == id,
                    selectedContainerColor = Color(0xFFEFF6FF),
                    selectedBorderColor = Color(0xFF1447E6),
                    content = {
                        Text(
                            text = name,
                        )
                    },
                    modifier = Modifier.height(52.dp),
                )
            },
        )

        ModalBodySelector(
            title = "담당자",
            essential = true,
            items = assignees.map { it.name },
            content = @Composable { name, id ->
                ModalOptionButton(
                    isSelected = modalState.assignee == id,
                    selectedBorderColor = Color(0xFF615FFF),
                    selectedContainerColor = Color(0xFFEFF6FF),
                    onClick = {
                        modalState.assignee = id
                    },
                    content = {
                        ModalOptionAssignee(
                            name = name,
                            modifier = Modifier,
                        )
                    },
                    modifier = Modifier.height(68.dp),
                )
            },
        )

        ModalAction(
            enabled = modalState.isValidContents,
            onClickCancel = { onClickCancel() },
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
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 672,
    heightDp = 820,
)
@Composable
private fun ModalBodyPreview() {
    val state = remember { ModalCreateFormState() }

    val assignees = listOf(
        Assignee("커비"),
        Assignee("바드"),
        Assignee("아오"),
        Assignee("하로"),
    )
    ModalBody(
        modalState = state,
        assignees = assignees,
        onClickCancel = {},
        onClickConfirm = {},
    )
}
