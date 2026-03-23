package woowacourse.kanban.board.component.taskmodal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.component.common.KanbanTitle

@Composable
fun ModalHeader(
    modifier: Modifier = Modifier,
    onClickCancel: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 24.dp),
    ) {
        KanbanTitle(
            title = "새 태스크 생성",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
        )

        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "모달 닫기",
            modifier = Modifier.size(20.dp)
                .clickable(onClick = { onClickCancel() }),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ModalHeaderPreview() {
    Box(
        modifier = Modifier.padding(all = 5.dp),
    ) {
        ModalHeader(onClickCancel = {})
    }
}
