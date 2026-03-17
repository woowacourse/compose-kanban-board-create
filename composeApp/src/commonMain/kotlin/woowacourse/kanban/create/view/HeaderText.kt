package woowacourse.kanban.create.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import woowacourse.kanban.HEADER_TEXT

@Composable
fun HeaderText(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(title, fontWeight = FontWeight.W500, fontSize = 14.sp, color = Color(HEADER_TEXT), modifier = modifier)
}
