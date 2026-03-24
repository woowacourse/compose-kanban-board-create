package woowacourse.kanban.board.ui.taskCard

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.design.Font
import woowacourse.kanban.board.model.Title

@Composable
fun TitleSection(
    title: Title,
    modifier: Modifier = Modifier,
    ) {
    Text(
        text = title.text,
        fontSize = Font.TITLE.size,
        overflow = TextOverflow.Ellipsis, maxLines = 1,
        modifier = Modifier.padding(8.dp),
    )
}

@Preview(showBackground = true)
@Composable
private fun TitleSectionPreview() {
    TitleSection(Title("LazyColumn 컴포넌트 구현"))
}
