package woowacourse.kanban.board.ui.taskCardForm

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.design.Font
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.ui.taskCard.AssigneeSection


@Composable
fun AssigneeInputSection(
    assignees: List<Assignee>,
    selected: Assignee,
    onSelect: (Assignee) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "담당자 *",
            fontSize = Font.FORMTITLE.size,
            fontWeight = Font.FORMTITLE.weight,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        )
        AssigneeField(
            assignees = assignees,
            selected = selected,
            onSelect = onSelect,
        )
    }
}

@Composable
private fun AssigneeField(
    assignees: List<Assignee>,
    selected: Assignee,
    onSelect: (Assignee) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        assignees.forEach { assignee ->
            val isSelected = assignee == selected
            val borderColor = if (isSelected) Color(0xFF615FFF) else Color(0xFFE5E7EB)
            val backgroundColor = if (isSelected) Color(0xFFEEF2FF) else Color(0xFFFFFFFF)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(
                        width = 2.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(10.dp),
                    )
                    .background(
                        color = backgroundColor,
                    )
                    .clickable { onSelect(assignee) }
                    .padding(4.dp),
            ) {
                AssigneeSection(assignee)
            }
        }
        Spacer(
            modifier = Modifier.weight(1f),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AssigneeInputSectionPreview() {
    val assignees = listOf(
        Assignee("다이노"),
        Assignee("페임스"),
    )
    MaterialTheme {
        AssigneeInputSection(
            assignees = assignees,
            selected = assignees.first(),
            onSelect = {},
        )
    }
}
