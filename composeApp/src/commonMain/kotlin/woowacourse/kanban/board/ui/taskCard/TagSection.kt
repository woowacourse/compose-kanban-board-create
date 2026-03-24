package woowacourse.kanban.board.ui.taskCard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.design.ColorPalette
import woowacourse.kanban.board.design.Font
import woowacourse.kanban.board.model.Tag

@Composable
fun TagSection(
    tag: Tag,
    modifier: Modifier = Modifier,
    ) {
    Text(
        text = tag.text,
        fontSize = Font.TAG.size,
        modifier = Modifier.background(ColorPalette.LighterGray, RoundedCornerShape(10.dp)).padding(4.dp),
    )
}

@Preview(showBackground = true)
@Composable
private fun TagSectionPreview() {
    TagSection(Tag("성능"))
}
