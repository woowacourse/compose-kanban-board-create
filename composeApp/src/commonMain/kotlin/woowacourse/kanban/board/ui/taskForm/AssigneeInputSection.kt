package woowacourse.kanban.board.ui.taskForm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.ui.taskCard.AssigneeSection
import woowacourse.kanban.board.util.ColorPalette
import woowacourse.kanban.board.util.Font
import woowacourse.kanban.board.util.Text as UiText

@Composable
fun AssigneeInputSection(assignees: List<Assignee>, selected: Assignee, onSelect: (Assignee) -> Unit) {
    Column {
        Text(
            text = UiText.LABEL_ASSIGNEE,
            style = Font.FORM_TITLE,
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
        )
        AssigneeField(
            assignees = assignees,
            selected = selected,
            onSelect = onSelect,
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

@Composable
fun AssigneeField(assignees: List<Assignee>, selected: Assignee, onSelect: (Assignee) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        assignees.forEach { assignee ->
            val isSelected = assignee == selected
            val borderColor = remember(isSelected) {
                if (isSelected) ColorPalette.AssigneeSelectedBorder
                else ColorPalette.AssigneeUnselectedBorder
            }
            val backgroundColor = remember(isSelected) {
                if (isSelected) ColorPalette.AssigneeSelectedBackground
                else ColorPalette.AssigneeUnselectedBackground
            }
            SelectionItem(
                modifier = Modifier.weight(1f),
                borderColor = borderColor,
                backgroundColor = backgroundColor,
                onClick = { onSelect(assignee) },
                padding = 4.dp,
            ) {
                AssigneeSection(assignee)
            }
        }
        Spacer(
            modifier = Modifier.weight(1f),
        )
    }
}
