package woowacourse.kanban.board.component.taskcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.constant.ColorPalette
import woowacourse.kanban.board.constant.TagsConst

@Preview(showBackground = true)
@Composable
private fun TagsPreview() {
    val tags = listOf("안녕", "하세요", "이건열글자넘는데열글자까지만나오나", "3", "4", "5", "이것도나오나이건나오면안되는데")
    Tags(
        tags = tags,
        maxTagCount = 6,
        maxTagTextLength = 10,
    )
}

@Composable
fun Tags(
    tags: List<String>?,
    modifier: Modifier = Modifier,
    maxTagCount: Int = TagsConst.MAX_TAGS,
    maxTagTextLength: Int = TagsConst.TAG_MAX_TEXT_LENGTH,
) {
    if (!tags.isNullOrEmpty()) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = modifier
                .fillMaxWidth(),
        ) {
            tags.take(maxTagCount).forEach { tag ->
                TagBox(tag.take(maxTagTextLength))
            }
        }
    }
}

@Composable
private fun TagBox(filteredTag: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(
                color = ColorPalette.Gray80,
            )
            .padding(vertical = 4.dp, horizontal = 6.dp),
    ) {
        Text(
            text = filteredTag,
            fontSize = 12.sp,
            color = ColorPalette.Gray20,
        )
    }
}
