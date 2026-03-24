package woowacourse.kanban.board.kanbanboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.launch
import woowacourse.kanban.board.createtaskcard.ui.ActionButton
import woowacourse.kanban.board.createtaskcard.ui.TaskCardDataInput
import woowacourse.kanban.board.constant.ColorPalette
import woowacourse.kanban.board.taskcard.domain.State
import woowacourse.kanban.board.createtaskcard.domain.TaskCardDataInputState
import woowacourse.kanban.board.kanbanboard.domain.TaskCardTable

@Composable
fun KanbanBoard(
    taskCardDataInputState: TaskCardDataInputState,
    taskCardTable: TaskCardTable,
    modifier: Modifier = Modifier
){
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var showDialog by remember { mutableStateOf(false) }

    val taskCardCount by remember { mutableIntStateOf(taskCardTable.allTaskCount) }
    LaunchedEffect(taskCardCount) {
        coroutineScope.launch {
            snackBarHostState.showSnackbar(
                message = "새로운 태스크가 추가되었습니다.",
                withDismissAction = true
            )
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .background(ColorPalette.KANBAN_BOARD_BACKGROUND)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 24.dp, top = 16.dp, bottom = 16.dp, end = 24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Column {
                        Text(
                            text = "Compose Desktop 칸반 보드",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "완료율 : ${taskCardTable.ratioOfDoneInt}% (${taskCardTable.doneTaskCount}/${taskCardTable.allTaskCount})",
                            modifier = Modifier.testTag("완료율")
                        )
                    }
                    ActionButton(
                        containerColor = ColorPalette.TASK_ADD_BUTTON,
                        text = "+새 태스크 생성",
                        onClick = { showDialog = true },
                    )
                    if(showDialog){
                        TaskCardDataInput(
                            state = taskCardDataInputState,
                            onCreate = {
                                taskCardTable.addCard(it)
                                showDialog = false
                            },
                            onCancel = {
                                showDialog = false
                                coroutineScope.launch {
                                    snackBarHostState.showSnackbar(
                                        message = "태스크 추가가 취소되었습니다.",
                                        withDismissAction = true
                                    )
                                }
                            }
                        )
                    }
                }
                LinearProgressIndicator(
                    progress = { taskCardTable.ratioOfDoneFloat },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    trackColor = Color(0xFFE5E7EB),
                    color = Color(0xFF4F39F6)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color(0xFFF9FAFB))
                    .padding(24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TaskBoard(State.TODO, taskCardTable.todoTable)
                TaskBoard(State.IN_PROGRESS, taskCardTable.inProgressTable)
                TaskBoard(State.DONE, taskCardTable.doneTable)
            }
        }
    }
}

@Preview(widthDp = 1200, heightDp = 900, showBackground = true)
@Composable
private fun KanbanBoardPreview() {
    val state = remember { TaskCardDataInputState() }
    val taskCardTable = remember { TaskCardTable() }
    KanbanBoard(
        state,
        taskCardTable
    )
}
