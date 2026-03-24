package woowacourse.kanban.board.ui.taskCardForm

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

@Composable
fun TaskCreateBottomSection(
    modifier: Modifier = Modifier,
    canSubmit: Boolean = true,
    onCreateClick: () -> Unit = {},
    onCancelClick: () -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        HorizontalDivider(modifier = modifier)
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.End,
        ) {
            Button(
                onClick = onCancelClick,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                ),
                modifier = modifier,
            ) {
                Text(
                    text = "취소",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Button(
                onClick = onCreateClick,
                enabled = canSubmit,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4F39F6),
                ),
                modifier = modifier
            ) {
                Text("생성")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskCreateBottomSectionPreview() {
    TaskCreateBottomSection()
}
