package woowacourse.kanban.board.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.cancel_button
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import woowacourse.kanban.board.CustomColor
import woowacourse.kanban.board.data.KanbanBoardSampleData
import woowacourse.kanban.newTaskCreate.component.CreateNewTaskDialogOverlay
import woowacourse.kanban.newTaskCreate.data.Task
import woowacourse.kanban.newTaskCreate.data.TaskStatus

@Composable
fun KanbanBoard(
    tasks: List<Task>,
) {
    // 새 태스크 생성 버튼을 눌렀을 때 상태를 변경하기 위함
    var openDialog by remember { mutableStateOf(false) }

    val taskState = remember { mutableStateListOf<Task>().apply { addAll(tasks) } }
    val assigneeById = remember {
        KanbanBoardSampleData.assignees.associate { it.id to it.nickname }
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .size(1295.dp, 909.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .horizontalScroll(rememberScrollState()),
        ) {
            // 헤더
            Header(
                totalCount = taskState.size,
                doneCount = taskState.count { it.status == TaskStatus.DONE },
                onClick = { openDialog = true },
            )
            Row(
                modifier = Modifier.padding(top = 24.dp, start = 24.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                // 칸반 카드들
                ProgressCard(
                    title = "To Do",
                    headerColor = CustomColor.Blue600,
                    bodyColor = CustomColor.Blue50,
                    borderColor = CustomColor.Blue200,
                    tasks = taskState.filter { it.status == TaskStatus.TO_DO },
                    assigneeById = assigneeById,
                    countTag = "todo_count_badge",
                )
                ProgressCard(
                    title = "In Progress",
                    headerColor = CustomColor.Orange700,
                    bodyColor = CustomColor.Yellow100,
                    borderColor = CustomColor.Yellow300,
                    tasks = taskState.filter { it.status == TaskStatus.IN_PROGRESS },
                    assigneeById = assigneeById,
                    countTag = "in_progress_count_badge",
                )
                ProgressCard(
                    title = "Done",
                    headerColor = CustomColor.Green700,
                    bodyColor = CustomColor.Green50,
                    borderColor = CustomColor.Green200,
                    tasks = taskState.filter { it.status == TaskStatus.DONE },
                    assigneeById = assigneeById,
                    countTag = "done_count_badge",
                )
            }
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .width(344.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
        ) { snackbarData ->
            Snackbar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = snackbarData.visuals.message,
                        modifier = Modifier.weight(1f),
                    )
                    IconButton(
                        onClick = { snackbarData.dismiss() },
                        modifier = Modifier.size(40.dp),
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.cancel_button),
                            contentDescription = "snackbar close",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                }
            }
        }
        if (openDialog) {
            CreateNewTaskDialogOverlay(
                assignees = KanbanBoardSampleData.assignees,
                onDismiss = { openDialog = false },
                onCreateTask = { task ->
                    taskState.add(task)
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "새로운 태스크가 추가되었습니다.",
                            // 현재는 Snackbar가 닫기 전까지 계속 남아있고
                            // 그 사이 새 태스크를 또 만들었을 때 다음 메시지가 대기 상태가 될 수 있다.
                            duration = SnackbarDuration.Indefinite,
                        )
                    }
                },
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 1295, heightDp = 909)
@Composable
private fun KanbanBoardPreview() {

    KanbanBoard(
        tasks = KanbanBoardSampleData.Tasks,
    )
}
