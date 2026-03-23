package woowacourse.kanban.board.component.newTaskCreate

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.component.screen.CreateNewTaskDialogState

@Composable
fun CreateNewTaskDialog(
    state: CreateNewTaskDialogState,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onTagsChange: (String) -> Unit,
    onStatusChange: (Int) -> Unit,
    onProfileChange: (Int) -> Unit,
    onClickCreateButton: () -> Unit,
    onClickCloseButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        TopBar(
            onClickCloseButton,
        )
        HorizontalDivider()
        NewTaskForm(
            title = state.title,
            onTitleChange = onTitleChange,
            description = state.description,
            onDescriptionChange = onDescriptionChange,
            tags = state.tags,
            onTagsChange = onTagsChange,
            selectedStatusIndex = state.selectedStatusIndex,
            statusOptions = state.statusOptions,
            onStatusChange = onStatusChange,
            profileOptions = state.profileOptions,
            selectedProfileIndex = state.selectedProfileIndex,
            onProfileChange = onProfileChange,
            modifier = Modifier.weight(1f),
        )
        HorizontalDivider(Modifier.padding(24.dp))
        Box(modifier = Modifier.padding(vertical = 16.dp)) {
            CreateNewTaskDialogBottom(
                onClickCloseButton = onClickCloseButton,
                onClickCreateButton = onClickCreateButton,
                isCreateEnabled = state.isCreateEnabled,
            )
        }
    }
}

@Preview(widthDp = 700)
@Composable
private fun CreateNewTaskDialogPreview() {
    CreateNewTaskDialog(
        state = CreateNewTaskDialogState(),
        onTitleChange = { },
        onDescriptionChange = { },
        onTagsChange = { },
        onStatusChange = { },
        onProfileChange = { },
        onClickCloseButton = { },
        onClickCreateButton = { },
    )
}
