package woowacourse.kanban.board.ui.taskForm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.model.Status
import woowacourse.kanban.board.util.ColorPalette
import woowacourse.kanban.board.util.Font
import woowacourse.kanban.board.util.Text as UiText

@Composable
fun StatusInputSection() {
    Column {
        Text(
            text = UiText.LABEL_STATUS,
            style = Font.FORM_TITLE,
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
        )
        StatusField()
    }
}

@Preview(showBackground = true)
@Composable
private fun StatusInputPreview() {
    MaterialTheme {
        StatusInputSection()
    }
}

@Composable
fun StatusField() {
    var selectedStatus by remember { mutableStateOf(Status.TODO) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Status.entries.forEach {
            val borderColor = remember(selectedStatus) {
                if (selectedStatus == it) ColorPalette.StatusSelectedBorder
                else ColorPalette.StatusUnSelectedBorder
            }
            val backgroundColor = remember(selectedStatus) {
                if (selectedStatus == it) ColorPalette.StatusSelectedBackground
                else ColorPalette.StatusUnSelectedBackground
            }
            val textColor = remember(selectedStatus) {
                if (selectedStatus == it) ColorPalette.StatusSelectedText
                else ColorPalette.StatusUnselectedText
            }
            SelectionItem(
                modifier = Modifier.weight(1f),
                borderColor = borderColor,
                backgroundColor = backgroundColor,
                onClick = { selectedStatus = it },
                padding = 8.dp,
            ) {
                Text(
                    text = UiText.statusLabel(it),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = textColor,
                )
            }
        }
    }
}
