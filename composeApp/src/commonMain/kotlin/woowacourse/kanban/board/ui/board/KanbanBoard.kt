package woowacourse.kanban.board.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.task_created_snackbar_message
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import woowacourse.kanban.board.domain.model.Card
import woowacourse.kanban.board.domain.model.Status
import woowacourse.kanban.board.ui.create.KanbanCreateDialog
import woowacourse.kanban.board.ui.preview.KanbanPreview

@Composable
fun KanbanBoard() {
    var showDialog by remember { mutableStateOf(false) }

    var todoCards by remember { mutableStateOf(emptyList<Card>()) }
    var inProgressCards by remember { mutableStateOf(emptyList<Card>()) }
    var doneCards by remember { mutableStateOf(emptyList<Card>()) }

    val totalCount = todoCards.size + inProgressCards.size + doneCards.size
    val doneCount = doneCards.size

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val taskCreatedSnackbarMessage = stringResource(Res.string.task_created_snackbar_message)

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 14.dp),
            ) { snackbarData ->
                Snackbar(
                    dismissAction = {
                        IconButton(
                            onClick = {
                                snackbarData.dismiss()
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "스낵바 닫기",
                            )
                        }
                    },
                ) {
                    Text(text = snackbarData.visuals.message)
                }
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            BoardInfoHeader(
                totalCount = totalCount,
                doneCount = doneCount,
                onTaskCreate = {
                    showDialog = true
                },
                modifier = Modifier
                    .background(Color.White)
                    .padding(horizontal = 24.dp, vertical = 16.dp),
            )
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Status.entries.forEach { status ->
                    val statusCards = when (status) {
                        Status.TODO -> todoCards
                        Status.IN_PROGRESS -> inProgressCards
                        Status.DONE -> doneCards
                    }
                    BoardTaskBox(
                        boardTaskStatus = status,
                        cards = statusCards,
                        boardTaskCount = statusCards.size,
                        headerColor = statusBackgroundColor(status),
                        borderColor = statusBorderColor(status),
                        mainColor = statusMainColor(status),
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
    if (showDialog) {
        KanbanCreateDialog(
            onDismissRequest = { showDialog = false },
            onCreateConfirm = { status, card ->
                showDialog = false
                when (status) {
                    Status.TODO -> todoCards = todoCards + card
                    Status.IN_PROGRESS -> inProgressCards = inProgressCards + card
                    Status.DONE -> doneCards = doneCards + card
                }
                scope.launch {
                    snackBarHostState.currentSnackbarData?.dismiss()
                    snackBarHostState.showSnackbar(
                        message = taskCreatedSnackbarMessage,
                        withDismissAction = true,
                        duration = SnackbarDuration.Indefinite,
                    )
                }
            },
        )
    }
}

private fun statusBackgroundColor(status: Status): Color {
    return when (status) {
        Status.TODO -> Color(0xFF155DFC)
        Status.IN_PROGRESS -> Color(0xFFE17100)
        Status.DONE -> Color(0xff00A63E)
    }
}

private fun statusMainColor(status: Status): Color {
    return when (status) {
        Status.TODO -> Color(0xffEFF6FF)
        Status.IN_PROGRESS -> Color(0xffFFFBEB)
        Status.DONE -> Color(0xFfF0FDF4)
    }
}

private fun statusBorderColor(status: Status): Color {
    return when (status) {
        Status.TODO -> Color(0xffBEDBFF)
        Status.IN_PROGRESS -> Color(0xffFEE685)
        Status.DONE -> Color(0xFFB9F8CF)
    }
}

@KanbanPreview
@Composable
private fun KanbanBoardPreview() {
    KanbanBoard()
}
