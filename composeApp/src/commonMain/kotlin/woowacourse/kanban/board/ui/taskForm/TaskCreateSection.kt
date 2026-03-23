package woowacourse.kanban.board.ui.taskForm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.model.Assignee

@Composable
fun TaskCreateSection(onDismiss: () -> Unit = {}, onCreate: () -> Unit = {}) {
    val assignees = remember {
        listOf(
            Assignee("다이노"),
            Assignee("페임스"),
        )
    }

    var title by remember { mutableStateOf("") }
    var isTitleError by remember { mutableStateOf(false) }
    var tags by remember { mutableStateOf("") }
    var isTagError by remember { mutableStateOf(false) }
    var description by remember { mutableStateOf("") }
    var assignee by remember { mutableStateOf(assignees.first()) }

    val isCreateEnabled = title.isNotBlank() && !isTitleError && !isTagError

    Column(
        modifier = Modifier.background(Color.White),
    ) {
        TaskCreateHeaderSection(onDismiss = onDismiss)
        Column(
            modifier = Modifier.padding(24.dp),
        ) {
            TitleInputSection(
                title = title,
                onTitleChange = { title = it },
                onErrorChange = { isTitleError = it },
            )
            DescriptionInputSection(
                description = description,
                onDescriptionChange = { description = it },
            )
            TagInputSection(
                onTagsChange = { tags = it },
                onErrorChange = { isTagError = it },
            )
            StatusInputSection()
            AssigneeInputSection(
                assignees = assignees,
                selected = assignee,
                onSelect = { assignee = it },
            )
        }
        TaskCreateBottomSection(
            isCreateEnabled = isCreateEnabled,
            onCancelClick = onDismiss,
            onCreateClick = onCreate,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskCreateSectionPreview() {
    MaterialTheme {
        TaskCreateSection()
    }
}
