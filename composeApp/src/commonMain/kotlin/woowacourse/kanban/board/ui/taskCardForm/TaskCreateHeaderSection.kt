package woowacourse.kanban.board.ui.taskCardForm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.design.Font

@Composable
fun TaskCreateHeaderSection(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "새 태스크 생성",
            fontSize = 20.sp,
            fontWeight = Font.FORMTITLE.weight,
            modifier = Modifier.padding(8.dp),
        )
        IconButton(
            modifier = Modifier.size(20.dp),
            onClick = onCloseClick,
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "닫기",
                tint = Color.Black,
                modifier = Modifier,
            )
        }
    }
    HorizontalDivider(modifier = modifier)
}

@Preview(showBackground = true)
@Composable
private fun TaskCreateHeaderSectionPreview() {
    TaskCreateHeaderSection()
}
