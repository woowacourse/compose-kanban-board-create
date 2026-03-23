package woowacourse.kanban.board.ui.taskForm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.util.Font
import woowacourse.kanban.board.util.Text as UiText

@Composable
fun TaskCreateHeaderSection(onDismiss: () -> Unit) {
    MaterialTheme {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = UiText.HEADER_CREATE_TASK,
                style = Font.FORM_HEADER,
                modifier = Modifier.padding(8.dp),
            )
            IconButton(
                modifier = Modifier.size(20.dp),
                onClick = onDismiss,

            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = UiText.ACTION_CLOSE,
                    tint = Color.Black,
                )
            }
        }
        HorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskCreateHeaderSectionPreview() {
    MaterialTheme {
        TaskCreateHeaderSection(
            onDismiss = {},
        )
    }
}
