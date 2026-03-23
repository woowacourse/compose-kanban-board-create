package woowacourse.kanban.board.ui.create.maincontent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.tag_label
import kanbanboard.composeapp.generated.resources.tag_label_placeholder
import kanbanboard.composeapp.generated.resources.tag_label_supporting
import org.jetbrains.compose.resources.stringResource
import woowacourse.kanban.board.ui.component.Label
import woowacourse.kanban.board.ui.component.SingleLineTextField
import woowacourse.kanban.board.ui.preview.KanbanPreview

@Composable
fun TagArea(
    value: String,
    onTagChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    isError: Boolean = false,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Label(stringResource(Res.string.tag_label))
        SingleLineTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = { onTagChange(it) },
            isError = isError,
            errorMessage = errorMessage,
            placeHolderMessage = stringResource(Res.string.tag_label_placeholder),
            supportingText = stringResource(Res.string.tag_label_supporting),
        )
    }
}

@Composable
@KanbanPreview
private fun TagPreview() {
    TagArea(
        value = "",
        onTagChange = {},
        isError = false,
        errorMessage = null,
    )
}
