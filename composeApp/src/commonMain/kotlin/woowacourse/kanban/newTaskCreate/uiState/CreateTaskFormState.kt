package woowacourse.kanban.newTaskCreate.uiState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import woowacourse.kanban.newTaskCreate.component.validateTags
import woowacourse.kanban.newTaskCreate.component.validateTitle
import woowacourse.kanban.newTaskCreate.data.Assignee
import woowacourse.kanban.newTaskCreate.data.TaskStatus

@Stable
class CreateTaskFormState(initialAssigneeId: String) {
    var titleText by mutableStateOf("")
    var description by mutableStateOf("")
    var tags by mutableStateOf("")
    var selectedStatus by mutableStateOf(TaskStatus.TO_DO)
    var selectedAssigneeId by mutableStateOf(initialAssigneeId)

    val isCreateEnabled: Boolean
        get() = validateTitle(
            titleText
        ) == null && validateTags(
            tags
        ) == null
}

@Composable
fun rememberCreateTaskFormState(assignees: List<Assignee>): CreateTaskFormState {
    val initialAssigneeId = assignees.firstOrNull()?.id.orEmpty()
    return remember(assignees) { CreateTaskFormState(initialAssigneeId) }
}
