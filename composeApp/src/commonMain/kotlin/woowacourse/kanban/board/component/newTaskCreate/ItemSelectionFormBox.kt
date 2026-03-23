package woowacourse.kanban.board.component.newTaskCreate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.component.task.Profile

@Composable
fun ItemSelectionFormBox(
    text: String,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(text = text)
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            content()
        }
    }
}

@Preview(widthDp = 672)
@Composable
private fun ItemSelectionFormBoxPreview() {
    ItemSelectionFormBox(
        text = "담당자",
        content = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                DefaultSelectButton(
                    isSelected = true,
                    onClick = {},
                    content = {
                        Profile(
                            nickname = "다이노",
                        )
                    },
                )
                DefaultSelectButton(
                    isSelected = true,
                    onClick = {},
                    content = {
                        Profile(
                            nickname = "페임스",
                        )
                    },
                )
            }
        },
    )
}
