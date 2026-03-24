package woowacourse.kanban.board.ui.taskCard

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.design.Font
import woowacourse.kanban.board.model.Description

@Composable
fun DescriptionSection(
    description: Description,
    modifier: Modifier = Modifier,
) {
    Text(
        text = description.text,
        fontSize = Font.DESCRIPTION.size,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        modifier = Modifier.padding(8.dp),
        color = Color.DarkGray,
    )
}

@Preview(showBackground = true)
@Composable
private fun DescriptionSectionPreview() {
    DescriptionSection(Description("세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다."))
}
