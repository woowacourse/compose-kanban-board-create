package woowacourse.kanban.board.ui.create.maincontent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.title_error_message
import kanbanboard.composeapp.generated.resources.title_label
import kanbanboard.composeapp.generated.resources.title_label_placeholder
import org.jetbrains.compose.resources.stringResource
import woowacourse.kanban.board.ui.component.Label
import woowacourse.kanban.board.ui.component.SingleLineTextField
import woowacourse.kanban.board.ui.preview.KanbanPreview

@Composable
fun TitleArea(
    value: String,
    onTitleChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Label(
            stringResource(Res.string.title_label),
            true,
        )
        SingleLineTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onTitleChange,
            isError = isError,
            errorMessage = stringResource(Res.string.title_error_message),
            placeHolderMessage = stringResource(Res.string.title_label_placeholder),
        )
    }
}

@Composable
@KanbanPreview
private fun TitlePreview() {
    var title by remember { mutableStateOf("") }
    TitleArea(
        value = title,
        onTitleChange = { },
    )
}
