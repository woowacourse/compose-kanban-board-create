package woowacourse.kanban.create.component.createTextInput

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.TEXT_FIELD_ERROR
import woowacourse.kanban.TEXT_FIELD_HINT

@Composable
fun HintText(
    modifier: Modifier = Modifier,
    hintText: String,
    isErrorText: Boolean = false,
) {
    Text(
        hintText,
        modifier = modifier.padding(horizontal = 16.dp),
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        color = if (isErrorText) Color(TEXT_FIELD_ERROR) else Color(TEXT_FIELD_HINT),
    )
}
