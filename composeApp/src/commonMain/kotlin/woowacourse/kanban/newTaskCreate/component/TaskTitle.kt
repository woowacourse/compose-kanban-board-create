package woowacourse.kanban.newTaskCreate.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.CustomColor

@Composable
fun TaskTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = CustomColor.Gray900,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
@Preview
private fun TaskTitlePreview(
    @PreviewParameter(TaskTitlePreviewProvider::class)
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        modifier = modifier,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = CustomColor.Gray900,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

private class TaskTitlePreviewProvider : PreviewParameterProvider<String> {
    override val values = sequenceOf(
        "Alice",
        "너무너무긴이름은\n말줄임표로출력합니다.",
        "Title",
    )
}
