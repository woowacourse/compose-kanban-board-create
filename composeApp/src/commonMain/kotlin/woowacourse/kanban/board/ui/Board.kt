package woowacourse.kanban.board.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import woowacourse.kanban.board.domain.TaskState

@Composable
fun Board() {
    val boardState = rememberBoardState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.size(height = 909.dp, width = 1295.dp),
        snackbarHost = {
            SnackbarHost(
                hostState = boardState.snackbarHostState,
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
                modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 16.dp),
            ) {
                BoardHeaderSection(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        boardState.showCardCreationPanel = true
                    },
                )
                BoardContents(
                    modifier = Modifier.fillMaxSize(),
                    boardState = boardState,
                )
            }

            if (boardState.showCardCreationPanel) {
                CardCreationPanel(
                    modifier = Modifier.align(Alignment.Center),
                    onAddItem = {
                        filterState(it, boardState).add(it)
                        scope.launch {
                            boardState.snackbarHostState.showSnackbar(message = "새로운 태스크가 추가되었습니다.", withDismissAction = true)
                        }
                    },
                    onShowCardCreationPanel = { boardState.showCardCreationPanel = it },
                )
            }
        }
    }
}

@Composable
private fun BoardHeaderSection(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
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
                    text = "완료율: 0% (0/0)",
                    fontWeight = FontWeight.W400,
                    color = Color(0xFF6A7282),
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = (-0.15).sp,
                )
            }

            Button(
                onClick = { onClick() },
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
            progress = { 0.5f },
            modifier = Modifier.fillMaxWidth().height(8.dp),
            color = ProgressIndicatorDefaults.linearColor,
            trackColor = ProgressIndicatorDefaults.linearTrackColor,
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
            gapSize = 0.dp,
        )
    }
}

@Composable
private fun BoardContents(
    modifier: Modifier = Modifier,
    boardState: BoardState,
) {
    Row(
        modifier = modifier.padding(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        StateColumnLayout(taskState = TaskState.TO_DO, cardUiStates = boardState.toDoCardUiStates)
        StateColumnLayout(taskState = TaskState.IN_PROGRESS, cardUiStates = boardState.inProgressCardUiStates)
        StateColumnLayout(taskState = TaskState.DONE, cardUiStates = boardState.doneCardUiStates)
    }
}

@Composable
fun StateColumnLayout(
    modifier: Modifier = Modifier,
    taskState: TaskState,
    cardUiStates: List<CardUiState>,
) {
    val testNum = "3"
    val headerColor: Color = when (taskState) {
        TaskState.TO_DO -> Color(0xFF155DFC)
        TaskState.IN_PROGRESS -> Color(0xFFE17100)
        TaskState.DONE -> Color(0xFF00A63E)
    }
    val contentColor: Color = when (taskState) {
        TaskState.TO_DO -> Color(0xFFEFF6FF)
        TaskState.IN_PROGRESS -> Color(0xFFFFFBEB)
        TaskState.DONE -> Color(0xFFF0FDF4)
    }
    val outlineColor: Color = when (taskState) {
        TaskState.TO_DO -> Color(0xFFBEDBFF)
        TaskState.IN_PROGRESS -> Color(0xFFFEE685)
        TaskState.DONE -> Color(0xFFB9F8CF)
    }

    Column(
        modifier = modifier.size(width = 320.dp, height = 748.dp).clip(RoundedCornerShape(10.dp)),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(48.dp).background(headerColor).padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = taskState.getTaskStateLabel(),
                color = Color(0xFFFFFFFF),
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                letterSpacing = (-0.31).sp,
                lineHeight = 24.sp,
            )

            Text(
                text = testNum,
                modifier = Modifier.clip(RoundedCornerShape(30.dp)).background(Color.White).padding(horizontal = 10.dp, vertical = 2.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                letterSpacing = (-0.15).sp,
                lineHeight = 20.sp,
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize().background(contentColor).border(width = 1.dp, color = outlineColor),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 17.dp, vertical = 16.dp),
        ) {
            items(cardUiStates.size) {
                Card(cardUiStates[it])
            }
        }
    }
}

private fun filterState(cardUiState: CardUiState, boardState: BoardState): SnapshotStateList<CardUiState> {
    return when (cardUiState.state) {
        TaskState.TO_DO -> boardState.toDoCardUiStates
        TaskState.IN_PROGRESS -> boardState.inProgressCardUiStates
        TaskState.DONE -> boardState.doneCardUiStates
    }
}

class BoardState {
    val toDoCardUiStates = mutableStateListOf<CardUiState>()
    val inProgressCardUiStates = mutableStateListOf<CardUiState>()
    val doneCardUiStates = mutableStateListOf<CardUiState>()
    var showCardCreationPanel by mutableStateOf(false)
    val snackbarHostState = SnackbarHostState()
}

@Composable
fun rememberBoardState(): BoardState = remember { BoardState() }
