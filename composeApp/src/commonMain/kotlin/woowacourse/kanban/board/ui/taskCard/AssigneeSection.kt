package woowacourse.kanban.board.ui.taskCard

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.design.Font
import woowacourse.kanban.board.model.Assignee

@Composable
fun AssigneeSection(
    assignee: Assignee,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "사용자 기본 이미지",
            modifier = modifier,
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = assignee.name,
            fontSize = Font.ASSIGNEE.size,
            fontWeight = Font.ASSIGNEE.weight,
            overflow = TextOverflow.Ellipsis, maxLines = 1,
            modifier = modifier,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AssigneeSectionPreview() {
    AssigneeSection(Assignee("다이노"))
}
