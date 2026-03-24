package woowacourse.kanban.board.ui.taskcard.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.constant.ColorPalette

@Preview(showBackground = true)
@Composable
private fun TitlePreview() {
    Title(
        title = "너 무 긴 제 목너 무 긴 제 목너 무 긴 제 목너 무 긴 제 목너 무 긴 제 목너 무 긴 제 목너 무 긴 제 목너 무 긴 제 목너 무 긴 제 목",
    )
}

@Composable
fun Title(title: String?, modifier: Modifier = Modifier) {
    if (!title.isNullOrEmpty()) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = ColorPalette.Gray10,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier,
        )
    }
}
