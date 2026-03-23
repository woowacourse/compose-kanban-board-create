package woowacourse.kanban.board.ui.create

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import woowacourse.kanban.board.domain.model.Card
import woowacourse.kanban.board.domain.model.Status
import woowacourse.kanban.board.ui.preview.KanbanPreview

@Composable
fun KanbanCreateDialog(
    onDismissRequest: () -> Unit,
    onCreateConfirm: (Status, Card) -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        content = {
            KanbanCreateDialogContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .clip(RoundedCornerShape(16.dp)),
                onDismiss = onDismissRequest,
                onCreateConfirm = onCreateConfirm,
            )
        },
    )
}

@Composable
@KanbanPreview
private fun KanbanDialogPreview() {
    KanbanCreateDialog(
        onDismissRequest = {},
        onCreateConfirm = { _, _ -> },
    )
}
