package woowacourse.kanban.board.ui.board

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.create_task_text
import org.jetbrains.compose.resources.stringResource
import woowacourse.kanban.board.ui.preview.KanbanPreview
import woowacourse.kanban.board.ui.theme.Blue

internal const val BOARD_PROGRESS_TEST_TAG = "board_progress_bar"

@Composable
fun BoardInfoHeader(
    totalCount: Int,
    doneCount: Int,
    onTaskCreate: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val complete = taskComplete(doneCount, totalCount)

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = "Compose Desktop 칸반 보드",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = "완료율: ${complete}% (${doneCount}/${totalCount})",
                )
            }

            BoardInfoHeaderContentButton(onTaskCreate = onTaskCreate)
        }
        BoardInfoHeaderProgressBar(taskComplete = complete)
    }
}

fun taskComplete(doneCount: Int, totalCount: Int): Int {
    if (totalCount == 0) return 0

    val safeDoneCount = doneCount.coerceIn(0, totalCount)
    val progress = (safeDoneCount * 100) / totalCount
    return progress
}

@Composable
private fun BoardInfoHeaderProgressBar(
    taskComplete: Int,
    modifier: Modifier = Modifier,
) {
    val progress = taskComplete.coerceIn(0, 100) / 100f
    LinearProgressIndicator(
        progress = { progress },
        modifier = modifier
            .fillMaxWidth()
            .height(8.dp)
            .testTag(BOARD_PROGRESS_TEST_TAG),
        color = Blue,
        trackColor = Color.LightGray,
        gapSize = 0.dp,
        drawStopIndicator = {},
    )
}

@Composable
private fun BoardInfoHeaderContentButton(
    onTaskCreate: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val createTaskText = stringResource(Res.string.create_task_text)

    Button(
        modifier = modifier,
        onClick = onTaskCreate,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Blue),
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = createTaskText,
            modifier = Modifier.size(20.dp),
        )
        Text(
            text = createTaskText,
            color = Color.White,
        )
    }
}

@KanbanPreview
@Composable
private fun BoardInfoHeaderPreview() {
    BoardInfoHeader(
        totalCount = 10,
        doneCount = 5,
        onTaskCreate = { }
    )
}

@Preview
@Composable
private fun BoardInfoHeader_ProgressPreview() {
    BoardInfoHeaderProgressBar(
        taskComplete = 50,
    )
}

@Preview
@Composable
private fun BoardInfoHeader_ContentButtonPreview() {
    BoardInfoHeaderContentButton(onTaskCreate = { })
}
