package woowacourse.kanban.board.ui.taskCardForm

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.TagGroup
import woowacourse.kanban.board.model.TaskStatus
import woowacourse.kanban.board.model.Title

data class TaskCreateFormState(
    val titleInput: String,
    val tagInput: String,
    val descriptionInput: String,
    val selectedAssignee: Assignee,
    val selectedTaskStatus: TaskStatus,
    val assignees: List<Assignee>,
    val titleErrorMessage: String?,
    val tagErrorMessage: String?,
    val canSubmit: Boolean,
)

data class TaskCreateFormIntent(
    val onTitleChange: (String) -> Unit,
    val onTagChange: (String) -> Unit,
    val onDescriptionChange: (String) -> Unit,
    val onStatusChange: (TaskStatus) -> Unit,
    val onAssigneeChange: (Assignee) -> Unit,
    val onCreateClick: () -> Unit,
    val onCancelClick: () -> Unit,
)

fun computeTaskCreateFormState(
    titleInput: String,
    tagInput: String,
    descriptionInput: String,
    selectedAssignee: Assignee,
    selectedTaskStatus: TaskStatus,
    assignees: List<Assignee>,
): TaskCreateFormState {
    val titleErrorMessage = Title.validate(titleInput)
    val tagErrorMessage = TagGroup.validate(tagInput)

    return TaskCreateFormState(
        titleInput = titleInput,
        tagInput = tagInput,
        descriptionInput = descriptionInput,
        selectedAssignee = selectedAssignee,
        selectedTaskStatus = selectedTaskStatus,
        assignees = assignees,
        titleErrorMessage = titleErrorMessage,
        tagErrorMessage = tagErrorMessage,
        canSubmit = titleErrorMessage == null && tagErrorMessage == null && titleInput.isNotBlank(),
    )
}

