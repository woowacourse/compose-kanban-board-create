package woowacourse.kanban.board.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import woowacourse.kanban.board.domain.Task
import woowacourse.kanban.board.domain.TaskState
import woowacourse.kanban.board.ui.taskcard.TaskCardUiState
import woowacourse.kanban.board.ui.taskcardform.TaskCardFormScreen

@Composable
fun BoardScreen(modifier: Modifier = Modifier, boardState: BoardState = remember { BoardState() }) {
    val scope = rememberCoroutineScope()

    val uiState = BoardUiState(
        showCardForm = boardState.showTaskCardForm,
        completeRate = boardState.completeRate,
        countOfDoneTasks = boardState.countOfDoneTasks,
        countOfAllTasks = boardState.countOfAllTasks,
        toDoTaskCards = boardState.cardsByState(TaskState.TO_DO),
        inProgressTaskCards = boardState.cardsByState(TaskState.IN_PROGRESS),
        doneTaskCards = boardState.cardsByState(TaskState.DONE)
    )

    BoardContent(
        uiState = uiState,
        snackbarHostState = boardState.snackbarHostState,
        onShowFormClick = { boardState.showTaskCardForm = true },
        onCloseFormClick = { boardState.showTaskCardForm = false },
        onCreateTask = { task ->
            boardState.createTaskCard(task)
            boardState.showTaskCardForm = false
            scope.launch {
                boardState.snackbarHostState.showSnackbar(
                    message = "새로운 태스크가 추가되었습니다.",
                    withDismissAction = true,
                )
            }
        },
        modifier = modifier.size(width = 1295.dp, height = 909.dp),
    )
}

@Composable
fun BoardContent(
    uiState: BoardUiState,
    snackbarHostState: SnackbarHostState,
    onShowFormClick: () -> Unit,
    onCloseFormClick: () -> Unit,
    onCreateTask: (Task) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.White,
        contentColor = Color.Black,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(bottom = 54.dp),
                snackbar = { snackbarData ->
                    Snackbar(snackbarData = snackbarData, modifier = Modifier.padding(horizontal = 16.dp))
                },
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                BoardHeaderSection(
                    completeRate = uiState.completeRate,
                    countOfDoneTasks = uiState.countOfDoneTasks,
                    countOfAllTasks = uiState.countOfAllTasks,
                    onCreateClick = onShowFormClick,
                    modifier = Modifier.fillMaxWidth(),
                )
                BoardBodySection(
                    toDoTaskCards = uiState.toDoTaskCards,
                    inProgressTaskCards = uiState.inProgressTaskCards,
                    doneTaskCards = uiState.doneTaskCards,
                    modifier = Modifier.fillMaxSize(),
                )
            }

            if (uiState.showCardForm) {
                TaskCardFormScreen(
                    modifier = Modifier.align(Alignment.Center),
                    onCreateCard = onCreateTask,
                    onClosePanelClick = onCloseFormClick,
                )
            }
        }
    }
}

@Composable
private fun BoardHeaderSection(
    completeRate: Float,
    countOfDoneTasks: Int,
    countOfAllTasks: Int,
    onCreateClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.border(width = 1.dp, color = Color(0xFFE5E7EB)).padding(horizontal = 24.dp, vertical = 16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = "Compose Desktop 칸반 보드 ",
                    fontWeight = FontWeight.W500,
                    fontSize = 24.sp,
                    lineHeight = 32.sp,
                    letterSpacing = 0.07.sp,
                )
                Text(
                    text = "완료율: ${(completeRate * 100).toInt()}% (${countOfDoneTasks}/${countOfAllTasks})",
                    fontWeight = FontWeight.W400,
                    color = Color(0xFF6A7282),
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = (-0.15).sp,
                )
            }

            Button(
                onClick = onCreateClick,
                modifier = Modifier,
                shape = RoundedCornerShape(20),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "새 태스크 생성 아이콘",
                        tint = Color.White,
                    )
                    Text(
                        text = "새 태스크 생성",
                        color = Color.White,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = (-0.31).sp,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LinearProgressIndicator(
            progress = { completeRate },
            modifier = Modifier.fillMaxWidth().height(8.dp),
            trackColor = Color(0xFFE5E7EB),
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
            gapSize = 0.dp,
            drawStopIndicator = {},
        )
    }
}

@Composable
private fun BoardBodySection(
    toDoTaskCards: List<TaskCardUiState>,
    inProgressTaskCards: List<TaskCardUiState>,
    doneTaskCards: List<TaskCardUiState>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.background(Color(0xFFF4F5F7)).padding(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        StateColumnLayout(taskState = TaskState.TO_DO, cards = toDoTaskCards)
        StateColumnLayout(taskState = TaskState.IN_PROGRESS, cards = inProgressTaskCards)
        StateColumnLayout(taskState = TaskState.DONE, cards = doneTaskCards)
    }
}
