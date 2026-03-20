package woowacourse.kanban.board.ui.board

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import woowacourse.kanban.board.domain.TaskState
import woowacourse.kanban.board.ui.CardCreationPanelScreen

@Composable
fun BoardScreen(boardState: BoardState = remember { BoardState() }) {
    BoardContent(boardState)
}

@Composable
private fun BoardContent(boardState: BoardState) {
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
                    boardState = boardState,
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
                CardCreationPanelScreen(
                    modifier = Modifier.align(Alignment.Center),
                    onAddItem = {
                        boardState.createCard(it)
                        scope.launch {
                            boardState.snackbarHostState.showSnackbar(
                                message = "새로운 태스크가 추가되었습니다.",
                                withDismissAction = true,
                            )
                        }
                    },
                    onShowCardCreationPanel = { boardState.showCardCreationPanel = it },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BoardContentPreview() {
    BoardContent(remember { BoardState() })
}

@Composable
private fun BoardHeaderSection(
    boardState: BoardState,
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
                    text = "완료율: ${(boardState.completeRate * 100).toInt()}% (${boardState.countOfDoneCards}/${boardState.countOfAllCard})",
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
            progress = { boardState.completeRate },
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
        TaskState.entries.forEach {
            StateColumnLayout(taskState = it, cards = boardState.cardsByState(it))
        }
    }
}
