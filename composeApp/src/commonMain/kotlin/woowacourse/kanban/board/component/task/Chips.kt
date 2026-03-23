package woowacourse.kanban.board.component.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.CustomColor

@Composable
fun Chips(
    tags: List<String>,
    modifier: Modifier = Modifier,
) {
    if (tags.isNotEmpty()) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = modifier,
        ) {
            tags.forEach { tag ->
                ChipBox(
                    tag,
                    Modifier
                        .background(
                            color = CustomColor.TAG_BACKGROUND.color,
                            shape = RoundedCornerShape(14.dp),
                        ).padding(vertical = 4.dp, horizontal = 6.dp)
                        .testTag("tag_item"),
                )
            }
        }
    }
}

@Composable
private fun ChipBox(filteredTag: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
    ) {
        Text(
            text = filteredTag,
            fontSize = 12.sp,
            color = CustomColor.DEFAULT_TEXT_COLOR.color,
        )
    }
}

@Preview
@Composable
private fun ChipsPreview(@PreviewParameter(ChipsPreviewProvider::class) tags: List<String>, modifier: Modifier = Modifier) {
    Chips(tags)
}

@Preview
@Composable
private fun ChipBoxPreview(@PreviewParameter(ChipBoxFilteredTagPreviewProvider::class) filteredTag: String) {
    ChipBox(filteredTag)
}

private class ChipsPreviewProvider : PreviewParameterProvider<List<String>?> {
    override val values = sequenceOf(
        listOf("tag1", "여러 자의 태그"),
        listOf("하나의 태그"),
        listOf("", " ", "  공백 확인  ", "  앞공백", "뒤공백  ", "\n\t"),
    )
}

private class ChipBoxFilteredTagPreviewProvider : PreviewParameterProvider<String> {
    override val values = sequenceOf<String>(
        "태그1",
        "태그 2",
        "너무너무긴태그",
        "최대다섯자",
    )
}
