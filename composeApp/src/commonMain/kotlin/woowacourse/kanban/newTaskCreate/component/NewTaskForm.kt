package woowacourse.kanban.newTaskCreate.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.newTaskCreate.data.Assignee
import woowacourse.kanban.newTaskCreate.data.TaskStatus

@Composable
fun NewTaskForm(
    title: String, // 제목
    onTitleChange: (String) -> Unit, // 재목의 textfield의 내용이 바뀔 경우
    description: String, // 설명
    onDescriptionChange: (String) -> Unit, // 설명의 textfield의 내용이 바뀔 경우
    tags: String, // , 로 이어진 태그 묶음들
    onTagsChange: (String) -> Unit, // 태그 textfield의 내용이 바뀔 경우
    selectedStatus: TaskStatus, // 선택한 상태 값(TO_DO, IN_PROGRESS, DONE)
    onStatusChange: (TaskStatus) -> Unit, // 선택한 상태가 변할 경우
    assignees: List<Assignee>,
    selectedAssigneeId: String, // 선택한 담당자 값(선택된 고유 담당자 번호)
    onAssignChange: (String) -> Unit, // 선택한 담당자 값이 변할 경우
) {
    val selectedAssignee = assignees.firstOrNull { it.id == selectedAssigneeId }

    Column(
        modifier = Modifier
            .size(672.dp, 654.dp)
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        DefaultTextField(
            modifier = Modifier.fillMaxWidth(),
            textFieldTag = "title_textField",
            titleText = "제목 *",
            value = title,
            onValueChange = onTitleChange,
            hintText = "태스크 제목을 입력하세요",
            defaultSupportingText = null,
            validate = {
                validateTitle(
                    it,
                )?.message()
            },
            minLines = 1,
            maxLines = 1,
        )
        DefaultTextField(
            modifier = Modifier.fillMaxWidth(),
            textFieldTag = "description_textField",
            titleText = "설명",
            value = description,
            onValueChange = onDescriptionChange,
            hintText = "태스크에 대한 자세한 설명을 입력하세요",
            defaultSupportingText = null,
            validate = {
                validateDescription(
                    it,
                )
            },
            minLines = 4,
            maxLines = 4,
        )
        DefaultTextField(
            modifier = Modifier.fillMaxWidth(),
            textFieldTag = "tags_textField",
            titleText = "태그",
            value = tags,
            onValueChange = onTagsChange,
            hintText = "태그를 쉼표로 구분하여 입력하세요 (예: 버그, 긴급)",
            defaultSupportingText = "5자 이내의 태그를 최대 5개까지 등록할 수 있습니다.",
            validate = {
                validateTags(
                    it,
                )?.message()
            },
            minLines = 1,
            maxLines = 1,
        )
        ItemSelectionFormBox(
            text = "상태 *",
            items = TaskStatus.entries,
            selectedItem = selectedStatus,
            onItemSelected = onStatusChange,
            width = 200.dp,
            height = 52.dp,
            itemTestTag = { status ->
                when (status) {
                    TaskStatus.TO_DO -> "status_option_todo"
                    TaskStatus.IN_PROGRESS -> "status_option_in_progress"
                    TaskStatus.DONE -> "status_option_done"
                }
            },
            itemContent = { status ->
                Text(
                    text = when (status) {
                        TaskStatus.TO_DO -> "To Do"
                        TaskStatus.IN_PROGRESS -> "In Progress"
                        TaskStatus.DONE -> "Done"
                    },
                )
            },
        )
        if (selectedAssignee != null) {
            ItemSelectionFormBox(
                text = "담당자 *",
                items = assignees,
                selectedItem = selectedAssignee,
                onItemSelected = { onAssignChange(it.id) },
                width = 200.dp,
                height = 68.dp,
                itemTestTag = { assignee -> "assignee_option_${assignee.id}" },
                itemContent = { assignee ->
                    ProfileCard(
                        nickname = assignee.nickname,
                        modifier = Modifier.align(Alignment.CenterStart),
                    )
                },
            )
        }
    }
}

@Preview(widthDp = 672, heightDp = 818)
@Composable
private fun NewTaskFormPreview() {
    val assignees = listOf(
        Assignee(id = "dino", nickname = "다이노"),
        Assignee(id = "fames", nickname = "페임스"),
    )
    var title by remember { mutableStateOf("태스크 제목을 입력하세요") }
    var description by remember { mutableStateOf("태스크에 대한 자세한 설명을 입력하세요") }
    var tags by remember { mutableStateOf("태그를 쉼표로 구분하여 입력하세요 (예: 버그,긴급)") }
    var selectedStatus by remember { mutableStateOf(TaskStatus.TO_DO) }
    var selectedAssigneeId by remember { mutableStateOf(assignees.first().id) }

    NewTaskForm(
        title = title,
        onTitleChange = { title = it },
        description = description,
        onDescriptionChange = { description = it },
        tags = tags,
        onTagsChange = { tags = it },
        selectedStatus = selectedStatus,
        onStatusChange = { selectedStatus = it },
        assignees = assignees,
        selectedAssigneeId = selectedAssigneeId,
        onAssignChange = { selectedAssigneeId = it },
    )
}
