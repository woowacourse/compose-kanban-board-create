package woowacourse.kanban.board.ui.component

import androidx.compose.runtime.Composable
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.done
import kanbanboard.composeapp.generated.resources.in_progress
import kanbanboard.composeapp.generated.resources.todo
import org.jetbrains.compose.resources.stringResource
import woowacourse.kanban.board.domain.model.Status

@Composable
fun Status.toDisplayText(): String {
    return when (this) {
        Status.TODO -> stringResource(Res.string.todo)
        Status.IN_PROGRESS -> stringResource(Res.string.in_progress)
        Status.DONE -> stringResource(Res.string.done)
    }
}
