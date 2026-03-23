package woowacourse.kanban.board.ui.kanbanBoard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import woowacourse.kanban.board.model.Status
import woowacourse.kanban.board.model.TaskCard
import woowacourse.kanban.board.ui.common.KanbanDialog
import woowacourse.kanban.board.ui.sample.TaskCardPreviewData
import woowacourse.kanban.board.ui.taskForm.TaskCreateSection
import woowacourse.kanban.board.util.Text

@Preview(showBackground = true, widthDp = 1000, heightDp = 900)
@Composable
private fun KanbanBoardSectionPreview() {
    val taskCards = TaskCardPreviewData().values.toList()
    MaterialTheme {
        KanbanBoardSection(taskCards = taskCards)
    }
}

@Composable
fun KanbanBoardSection(taskCards: List<TaskCard>) {
    var showDialog by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val counts = calculateBoardCounts(taskCards)
    val totalCount = counts.totalCount
    val doneCount = counts.doneCount

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        androidx.compose.foundation.layout.Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            KanbanBoardHeaderSection(
                totalCount = totalCount,
                doneCount = doneCount,
                onCreateClick = { showDialog = true },
            )
            HorizontalDivider()
            KanbanBoardBodySection(taskCards)
        }

        if (showDialog) {
            KanbanDialog(
                showDialog = showDialog,
                onDismiss = { showDialog = false },
                content = {
                    TaskCreateSection(
                        onDismiss = { showDialog = false },
                        onCreate = {
                            showDialog = false
                            coroutineScope.launch {
                                snackBarHostState.showSnackbar(
                                    message = Text.ALARM_NEW_TASK,
                                    duration = SnackbarDuration.Short,
                                )
                            }
                        },
                    )
                },
            )
        }

        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter),
        )
    }
}

internal data class BoardCounts(val totalCount: Int, val doneCount: Int)

internal fun calculateBoardCounts(taskCards: List<TaskCard>): BoardCounts {
    val totalCount = taskCards.size
    val doneCount = taskCards.count { it.status == Status.DONE }
    return BoardCounts(totalCount, doneCount)
}
