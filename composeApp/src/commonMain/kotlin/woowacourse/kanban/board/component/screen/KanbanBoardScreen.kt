package woowacourse.kanban.board.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.launch
import woowacourse.kanban.board.component.kanbanboard.KanbanBoard
import woowacourse.kanban.board.component.kanbanboard.KanbanBoardScreenTopBar
import woowacourse.kanban.board.component.newTaskCreate.CreateNewTaskDialog
import woowacourse.kanban.board.data.Nickname
import woowacourse.kanban.board.data.Tasks

@Composable
fun KanbanBoardScreen() {
    val tasks = remember { Tasks(mutableStateListOf()) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var isCreatingNewTask by remember { mutableStateOf(false) }
    var dialogState by remember { mutableStateOf(CreateNewTaskDialogState()) }

    if (isCreatingNewTask) Dialog(
        onDismissRequest = {
            isCreatingNewTask = false
            dialogState = CreateNewTaskDialogState()
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false,
        ),
    ) {
        CreateNewTaskDialog(
            state = dialogState,
            onTitleChange = { dialogState = dialogState.copy(title = it) },
            onDescriptionChange = { dialogState = dialogState.copy(description = it) },
            onTagsChange = { dialogState = dialogState.copy(tags = it) },
            onStatusChange = { dialogState = dialogState.copy(selectedStatusIndex = it) },
            onProfileChange = { dialogState = dialogState.copy(selectedProfileIndex = it) },
            onClickCreateButton = {
                tasks.addNewTask(
                    dialogState.title,
                    dialogState.description,
                    dialogState.tags,
                    dialogState.statusOptions[dialogState.selectedStatusIndex],
                    Nickname(dialogState.profileOptions[dialogState.selectedProfileIndex]),
                )
                scope.launch {
                    snackbarHostState.showSnackbar("새로운 태스크가 추가되었습니다.")
                }
                isCreatingNewTask = false
                dialogState = CreateNewTaskDialogState()
            },
            onClickCloseButton = {
                isCreatingNewTask = false
                dialogState = CreateNewTaskDialogState()
            },
            modifier = Modifier
                .padding(vertical = 15.dp)
                .background(color = Color.White)
                .width(750.dp),
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
        ) {
            KanbanBoardScreenTopBar(
                tasksCount = tasks.tasksSize(),
                completeCount = tasks.doneTasksSize(),
                completeRate = tasks.calculateDoneTasksRatio(),
                onClickCreateNewTaskButton = { isCreatingNewTask = true },
            )
            KanbanBoard(
                tasks = tasks,
            )
        }
    }
}

@Preview(widthDp = 1200)
@Composable
private fun KanbanBoardScreenPreview() {
    KanbanBoardScreen()
}
