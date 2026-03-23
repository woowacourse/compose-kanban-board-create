package woowacourse.kanban.board.ui.create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.domain.model.Card
import woowacourse.kanban.board.domain.model.Status
import woowacourse.kanban.board.domain.model.Tag
import woowacourse.kanban.board.domain.model.User
import woowacourse.kanban.board.domain.validator.validateTagInput
import woowacourse.kanban.board.ui.create.maincontent.ContentArea
import woowacourse.kanban.board.ui.create.maincontent.ManagerSelector
import woowacourse.kanban.board.ui.create.maincontent.StatusSelector
import woowacourse.kanban.board.ui.create.maincontent.TagArea
import woowacourse.kanban.board.ui.create.maincontent.TitleArea
import woowacourse.kanban.board.ui.preview.KanbanPreview

@Composable
fun KanbanCreateDialogContent(
    onDismiss: () -> Unit,
    onCreateConfirm: (Status, Card) -> Unit,
    modifier: Modifier = Modifier,
) {
    var title by remember { mutableStateOf("") }
    var isTitleError by remember { mutableStateOf(false) }

    var content by remember { mutableStateOf("") }

    var tag by remember { mutableStateOf("") }
    var isTagError by remember { mutableStateOf(false) }
    var isTagErrorMessage: String? by remember { mutableStateOf(null) }

    var status by remember { mutableStateOf(Status.TODO) }

    var selectedUser by remember { mutableStateOf(User.managersList.first()) }

    Column(
        modifier = modifier
            .background(Color.White),
    ) {
        KanbanCreateHeader(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 24.dp, vertical = 28.dp),
            onDismiss = onDismiss,
        )

        HorizontalDivider()

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {

            TitleArea(
                value = title,
                onTitleChange = {
                    title = it
                    isTitleError = title.isBlank()
                },
                isError = isTitleError,
            )

            ContentArea(
                value = content,
                onContentChange = {
                    content = it
                },
            )

            TagArea(
                value = tag,
                onTagChange = {
                    tag = it
                    isTagErrorMessage = validateTagInput(it)
                    isTagError = isTagErrorMessage != null
                },
                errorMessage = isTagErrorMessage,
                isError = isTagError,
            )

            StatusSelector(
                selectedStatus = status,
                onStatusChange = {
                    status = it
                },
            )

            ManagerSelector(
                managers = User.managersList,
                selectedUser = selectedUser,
                onUserChange = {
                    selectedUser = it
                },
            )
        }
        HorizontalDivider()
        KanbanCreateFooter(
            onClickCancel = onDismiss,
            onClickConfirm = {
                val tags = parseTagInput(tag)
                onCreateConfirm(
                    status,
                    Card(
                        title = title,
                        content = content.takeIf { it.isNotBlank() },
                        tags = tags,
                        user = selectedUser,
                    ),
                )
            },
            enabled = !isTitleError && title.isNotBlank() && !isTagError,
        )
    }
}

private fun parseTagInput(tagInput: String): List<Tag> {
    if (tagInput.isBlank()) return emptyList()

    return tagInput
        .split(",")
        .map { it.trim() }
        .filter { it.isNotEmpty() }
        .map(::Tag)
}

@Composable
@KanbanPreview
fun KanbanCreateDialogContentPreview() {
    KanbanCreateDialogContent(
        onDismiss = {},
        onCreateConfirm = { _, _ -> },
    )
}
