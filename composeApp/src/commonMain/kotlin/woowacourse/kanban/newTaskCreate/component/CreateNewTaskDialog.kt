package woowacourse.kanban.newTaskCreate.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.newTaskCreate.data.Assignee
import woowacourse.kanban.newTaskCreate.data.Task
import woowacourse.kanban.newTaskCreate.uiState.rememberCreateTaskFormState

@Composable
fun CreateNewTaskDialog(
    assignees: List<Assignee>,
    onDismiss: () -> Unit,
    onCreateTask: (Task) -> Unit,
) {
    val formState = rememberCreateTaskFormState(assignees)
    Column(
        modifier = Modifier
            .size(672.dp, 818.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
    ) {
        // 상단부
        TopBar(onDismiss)
        HorizontalDivider()
        // 중단부
        NewTaskForm(
            title = formState.titleText,
            onTitleChange = { formState.titleText = it },
            description = formState.description,
            onDescriptionChange = { formState.description = it },
            tags = formState.tags,
            onTagsChange = { formState.tags = it },
            selectedStatus = formState.selectedStatus,
            onStatusChange = { formState.selectedStatus = it },
            assignees = assignees,
            selectedAssigneeId = formState.selectedAssigneeId,
            onAssignChange = { formState.selectedAssigneeId = it },
        )
        HorizontalDivider(Modifier.padding(24.dp))
        // 하단부
        CreateNewTaskDialogBottom(
            isCreateEnabled = formState.isCreateEnabled, // 생성 버튼 활성화
            onCreate = {
                val task = Task(
                    taskTitle = formState.titleText,
                    taskScript = formState.description,
                    tags = formState.tags
                        .split(",")
                        .map { it.trim() }
                        .filter { it.isNotEmpty() },
                    assigneeId = formState.selectedAssigneeId,
                    status = formState.selectedStatus,
                )
                onCreateTask(task)
                // 내부 검증만 통과하면 생성-다이얼로그 닫기가 묶여서 돌아가므로 실패할 경우도 고려해보라고 피드백 주심
                onDismiss()
            }, // 클릭하면 taskCard가 만들어지도록
        )
    }
}

@Preview(widthDp = 672, heightDp = 818)
@Composable
fun CreateNewTaskDialogPreview(
) {
    val assignees = listOf(
        Assignee(id = "dino", nickname = "다이노"),
        Assignee(id = "fames", nickname = "페임스"),
    )
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
    ) {
        CreateNewTaskDialog(
            assignees = assignees,
            onDismiss = {},
            onCreateTask = {},
        )
    }
}
