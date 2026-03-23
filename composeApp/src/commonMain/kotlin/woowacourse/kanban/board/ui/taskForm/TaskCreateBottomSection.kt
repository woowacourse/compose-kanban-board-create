package woowacourse.kanban.board.ui.taskForm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.util.ColorPalette
import woowacourse.kanban.board.util.Text as UiText

@Preview(showBackground = true)
@Composable
private fun ButtonFieldDisabledPreview() {
    MaterialTheme {
        TaskCreateBottomSection(
            isCreateEnabled = true,
            onCancelClick = {},
            onCreateClick = {},
        )
    }
}

@Composable
fun TaskCreateBottomSection(isCreateEnabled: Boolean = true, onCancelClick: () -> Unit, onCreateClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        HorizontalDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.End,
        ) {
            Button(
                onClick = onCancelClick,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                ),
            ) {
                Text(
                    text = UiText.ACTION_CANCEL,
                    style = MaterialTheme.typography.titleMedium,
                )
            }

            Button(
                onClick = onCreateClick,
                enabled = isCreateEnabled,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorPalette.ActiveButton,
                ),
            ) {
                Text(UiText.ACTION_CREATE)
            }
        }
    }
}
