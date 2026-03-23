package woowacourse.kanban.board.ui.taskCard

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.util.Font
import woowacourse.kanban.board.util.Text as UiText

@Composable
fun AssigneeSection(assignee: Assignee) {
    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = UiText.CONTENT_USER_DEFAULT_IMAGE,
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = assignee.name,
            style = Font.ASSIGNEE,
            overflow = TextOverflow.Ellipsis, maxLines = 1,
        )
    }
}
