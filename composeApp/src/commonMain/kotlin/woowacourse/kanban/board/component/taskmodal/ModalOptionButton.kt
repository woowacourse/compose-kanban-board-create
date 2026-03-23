package woowacourse.kanban.board.component.taskmodal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.component.common.KanbanAssigneeProfile

@Composable
fun ModalOptionButton(
    selectedContainerColor: Color,
    selectedBorderColor: Color,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    val containerColor = if (isSelected) selectedContainerColor else Color.White
    val borderColor = if (isSelected) selectedBorderColor else Color(0xFFE5E7EB)
    Box(
        modifier = modifier
            .width(200.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(containerColor)
            .border(
                width = 1.5.dp,
                color = borderColor,
                shape = RoundedCornerShape(10.dp),
            )
            .clickable(
                enabled = true,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun ModalOptionButtonPreview() {
    var enabled by remember { mutableStateOf(value = false) }
    ModalOptionButton(
        onClick = {},
        content = {
            Text(
                text = "In Progress",
            )
        },
        isSelected = enabled,
        selectedContainerColor = Color(0xFFEFF6FF),
        selectedBorderColor = Color(0xFF1447E6),
    )
}

@Preview(showBackground = true)
@Composable
private fun ModalOptionAssigneePreview() {
    var enabled by remember { mutableStateOf(value = false) }
    ModalOptionButton(
        isSelected = enabled,
        selectedContainerColor = Color(0xFFEFF6FF),
        selectedBorderColor = Color(0xFF615FFF),
        onClick = {},
        content = {
            ModalOptionAssignee(
                name = "다이노",
                modifier = Modifier,
            )
        },
    )
}

@Composable
fun ModalOptionAssignee(
    name: String,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 12.dp),
    ) {

        KanbanAssigneeProfile(
            crewName = name,
        )
    }
}
