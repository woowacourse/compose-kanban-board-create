package woowacourse.kanban.board.ui.create.maincontent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.content_label
import kanbanboard.composeapp.generated.resources.content_label_placeholder
import org.jetbrains.compose.resources.stringResource
import woowacourse.kanban.board.ui.component.Label
import woowacourse.kanban.board.ui.preview.KanbanPreview
import woowacourse.kanban.board.ui.theme.Gray
import woowacourse.kanban.board.ui.theme.Gray500
import woowacourse.kanban.board.ui.theme.Red

@Composable
fun ContentArea(
    value: String,
    onContentChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(1f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Label(stringResource(Res.string.content_label))
        OutlinedTextField(
            value = value,
            onValueChange = {
                onContentChange(it)
            },
            modifier = Modifier.fillMaxWidth(1f),
            placeholder = {
                Text(
                    text = stringResource(Res.string.content_label_placeholder),
                    color = Gray,
                )
            },
            minLines = 6,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                unfocusedBorderColor = Gray500,
                errorBorderColor = Red,
            ),
        )
    }
}

@Composable
@KanbanPreview
private fun ContentPreview() {
    ContentArea(
        value = "",
        onContentChange = {},
    )
}
