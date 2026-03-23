package woowacourse.kanban.board.ui.taskCard

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.util.Font

@Composable
fun DescriptionSection(description: String) {
    Text(
        text = description,
        style = Font.DESCRIPTION,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        modifier = Modifier.padding(8.dp),
        color = Color.DarkGray,
    )
}
