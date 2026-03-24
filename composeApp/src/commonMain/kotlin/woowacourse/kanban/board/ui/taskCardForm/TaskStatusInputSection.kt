package woowacourse.kanban.board.ui.taskCardForm

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.design.Font
import woowacourse.kanban.board.model.TaskStatus


@Composable
fun TaskStatusInputSection(
    selectedTaskStatus: TaskStatus,
    onStatusChange: (TaskStatus) -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "상태 *",
        fontSize = Font.FORMTITLE.size,
        fontWeight = Font.FORMTITLE.weight,
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
    )
    TaskStatusField(
        selectedTaskStatus = selectedTaskStatus,
        onSelect = onStatusChange,
    )

}

@Composable
private fun TaskStatusField(
    selectedTaskStatus: TaskStatus,
    onSelect: (TaskStatus) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        TaskStatus.entries.forEach {
            val borderColor = if (selectedTaskStatus == it) Color(0xFF1447E6) else Color(0xFFE5E7EB)
            val backgroundColor = if (selectedTaskStatus == it) Color(0xFFEEF2FF) else Color(0xFFFFFFFF)
            val textColor = if (selectedTaskStatus == it) Color(0xFF1447E6) else Color(0xFF364153)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(
                        width = 2.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(10.dp),
                    )
                    .background(
                        color = backgroundColor,
                    )
                    .clickable { onSelect(it) }
                    .padding(8.dp),
            ) {
                Text(
                    text = it.text,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = textColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskStatusInputPreview() {
    MaterialTheme {
        TaskStatusInputSection(
            selectedTaskStatus = TaskStatus.TODO,
            onStatusChange = {},
        )
    }
}
