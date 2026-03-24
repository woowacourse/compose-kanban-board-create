package woowacourse.kanban.board.ui.taskCardForm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.Description
import woowacourse.kanban.board.model.TagGroup
import woowacourse.kanban.board.model.TaskCard
import woowacourse.kanban.board.model.TaskStatus
import woowacourse.kanban.board.model.Title

@Composable
fun TaskCreateSection(
    modifier: Modifier = Modifier,
    onTaskCreate: (TaskCard) -> Unit = {},
    onCancel: () -> Unit = {},
) {
    val assignees = remember {
        listOf(
            Assignee("다이노"),
            Assignee("페임스"),
        )
    }

    var titleInput by remember { mutableStateOf("") }
    var tagInput by remember { mutableStateOf("") }
    var descriptionInput by remember { mutableStateOf("") }
    var selectedAssignee by remember { mutableStateOf(assignees.first()) }
    var selectedTaskStatus by remember { mutableStateOf(TaskStatus.TODO) }

    val state by remember(
        titleInput,
        tagInput,
        descriptionInput,
        selectedAssignee,
        selectedTaskStatus,
    ) {
        derivedStateOf {
            computeTaskCreateFormState(
                titleInput = titleInput,
                tagInput = tagInput,
                descriptionInput = descriptionInput,
                selectedAssignee = selectedAssignee,
                selectedTaskStatus = selectedTaskStatus,
                assignees = assignees,
            )
        }
    }

    val intent = TaskCreateFormIntent(
        onTitleChange = { titleInput = it },
        onTagChange = { tagInput = it },
        onDescriptionChange = { descriptionInput = it },
        onStatusChange = { selectedTaskStatus = it },
        onAssigneeChange = { selectedAssignee = it },
        onCreateClick = {
            if (state.canSubmit) {
                val taskCard = TaskCard(
                    title = Title(state.titleInput),
                    status = state.selectedTaskStatus,
                    description = Description(state.descriptionInput),
                    tags = TagGroup.parse(state.tagInput),
                    assignee = state.selectedAssignee,
                )
                onTaskCreate(taskCard)

                titleInput = ""
                tagInput = ""
                descriptionInput = ""
                selectedAssignee = assignees.first()
                selectedTaskStatus = TaskStatus.TODO
            }
        },
        onCancelClick = onCancel,
    )

    TaskCreateContent(
        state = state,
        intent = intent,
        modifier = modifier,
    )
}

@Composable
private fun TaskCreateContent(
    state: TaskCreateFormState,
    intent: TaskCreateFormIntent,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        TaskCreateHeaderSection(onCloseClick = intent.onCancelClick)
        Column(modifier = Modifier.padding(24.dp)) {
            TitleInputSection(
                title = state.titleInput,
                onTitleChange = intent.onTitleChange,
                errorMessage = state.titleErrorMessage,
            )
            DescriptionInputSection(
                description = state.descriptionInput,
                onDescriptionChange = intent.onDescriptionChange,
            )
            TagInputSection(
                tags = state.tagInput,
                onTagsChange = intent.onTagChange,
                errorMessage = state.tagErrorMessage,
            )
            TaskStatusInputSection(
                selectedTaskStatus = state.selectedTaskStatus,
                onStatusChange = intent.onStatusChange,
            )
            AssigneeInputSection(
                assignees = state.assignees,
                selected = state.selectedAssignee,
                onSelect = intent.onAssigneeChange,
            )
        }
        TaskCreateBottomSection(
            canSubmit = state.canSubmit,
            onCreateClick = intent.onCreateClick,
            onCancelClick = intent.onCancelClick,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskCreateSectionPreview() {
    MaterialTheme {
        TaskCreateSection()
    }
}
