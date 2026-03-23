package woowacourse.kanban.board.component.taskboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import woowacourse.kanban.board.component.taskcard.KanbanCardForm
import woowacourse.kanban.board.component.taskmodal.ModalCreateFormState
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.TaskState

@Composable
fun KanbanBoard(modifier: Modifier = Modifier) {

    val state = remember { KanbanBoardState() }

    val snackbarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val assignees = remember {
        mutableStateListOf(
            Assignee("커비"),
            Assignee("바드"),
            Assignee("아오"),
        )
    }
    Box(modifier = modifier) {
        if (state.showDialog) {
            KanbanBoardDialog(
                assignees = assignees,
                modalState = state.modalState,
                onClickCancel = { state.showDialog = false },
                onClickConfirm = {
                    state.addTask(it)
                    state.showDialog = false
                    scope.launch {
                        snackbarState.showSnackbar(
                            message = "${it.title} 태스크가 생성되었습니다.",
                            duration = SnackbarDuration.Short,
                        )
                    }
                },
            )
        }
        Column(modifier = Modifier) {
            KanbanTaskBoardHeader(
                onClick = { state.showDialog = state.showDialog.not() },
                taskList = state.taskList,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                    )
                    .padding(24.dp),
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .background(color = Color(0xFFF9FAFB))
                    .padding(24.dp),
            ) {
                TaskState.entries.forEach { taskState ->
                    val stateTask = state.taskGroup[taskState] ?: emptyList()
                    val holderColor = when (taskState) {
                        TaskState.IN_PROGRESS -> HolderColor(
                            headerContainer = Color(0xFFE17100),
                            contentContainer = Color(0xFFFFFBEB),
                            contentBorder = Color(0xFFFEE685),
                        )

                        TaskState.TODO -> HolderColor(
                            headerContainer = Color(0xFF155DFC),
                            contentContainer = Color(0xFFEFF6FF),
                            contentBorder = Color(0xFFBEDBFF),
                        )

                        TaskState.DONE -> HolderColor(
                            headerContainer = Color(0xFF00A63E),
                            contentContainer = Color(0xFFF0FDF4),
                            contentBorder = Color(0xFFB9F8CF),
                        )
                    }
                    KanbanCardHolder(
                        tasks = stateTask,
                        state = taskState,
                        holderColor = holderColor,
                    )
                }
            }
        }
        SnackbarHost(
            hostState = snackbarState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
        )
    }
}


@Preview(
    showBackground = true,
    widthDp = 1300,
    heightDp = 910,
)
@Composable
fun KanbanBoardPreview() {
    KanbanBoard()
}
