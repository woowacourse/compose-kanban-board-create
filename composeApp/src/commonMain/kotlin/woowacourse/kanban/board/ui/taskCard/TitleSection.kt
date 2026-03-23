package woowacourse.kanban.board.ui.taskCard

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.util.Font

@Composable
fun TitleSection(title: String) {
    Text(
        text = title,
        style = Font.TITLE,
        overflow = TextOverflow.Ellipsis, maxLines = 1,
        modifier = Modifier.padding(8.dp),
    )
}
