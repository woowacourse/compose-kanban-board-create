package woowacourse.kanban.newTaskCreate.component

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
fun TagList(tags: List<String>, tagsModifier: Modifier = Modifier) {
    if (tags != emptyList<String>()) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = tagsModifier,
        ) {
            tags.take(5).forEach { tag ->
                TagBox(
                    tag.take(5),
                    Modifier
                        .background(
                            color = CustomColor.Gray100,
                            shape = RoundedCornerShape(14.dp),
                        ).padding(vertical = 4.dp, horizontal = 6.dp)
                        .testTag("tag_item"),
                )
            }
        }
    }
}

@Composable
fun TagBox(filteredTag: String, tagsModifier: Modifier = Modifier) {
    Box(
        modifier = tagsModifier,
    ) {
        Text(
            text = filteredTag,
            fontSize = 12.sp,
            color = CustomColor.Gray700,
        )
    }
}

// 원래 함수가 바뀔 때마다 Preview 함수도 바꿔야함.
@Composable
@Preview
private fun TagsPreview(@PreviewParameter(TagsPreviewProvider::class) tags: List<String>, tagsModifier: Modifier = Modifier) {
    if (tags != emptyList<String>()) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = tagsModifier,
        ) {
            tags.take(5).forEach { tag ->
                TagBox(
                    tag.take(5),
                    Modifier
                        .background(
                            color = CustomColor.Gray100,
                            shape = RoundedCornerShape(14.dp),
                        ).padding(vertical = 4.dp, horizontal = 6.dp)
                        .testTag("tag_item"),
                )
            }
        }
    }
}

@Composable
@Preview
private fun TagBoxPreview(@PreviewParameter(TagBoxFilteredTagPreviewProvider::class) filteredTag: String) {
    Box(
        modifier = Modifier
            .background(
                color = CustomColor.Gray100,
                shape = RoundedCornerShape(14.dp),
            )
            .padding(vertical = 4.dp, horizontal = 6.dp),
    ) {
        Text(
            text = filteredTag,
            fontSize = 12.sp,
            color = CustomColor.Gray700,
        )
    }
}

private class TagsPreviewProvider : PreviewParameterProvider<List<String>?> {
    override val values = sequenceOf(
        listOf("tag1", "tags 테스트", "여러 개의 태그"),
        listOf("하나의 태그"),
        listOf("", " ", "  공백 확인  ", "  앞공백", "뒤공백  "),
    )
}

private class TagBoxFilteredTagPreviewProvider : PreviewParameterProvider<String> {
    override val values = sequenceOf<String>(
        "태그1",
        "태그 2",
        "태그 3",
        "너무너무긴태그",
        "최대다섯자",
    )
}
