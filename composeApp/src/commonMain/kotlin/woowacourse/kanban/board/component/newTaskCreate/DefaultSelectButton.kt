package woowacourse.kanban.board.component.newTaskCreate

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults.outlinedButtonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.CustomColor
import woowacourse.kanban.board.component.task.Profile

@Composable
fun DefaultSelectButton(
    isSelected: Boolean,
    onClick: () -> Unit,
    alignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.width(200.dp),
        colors = outlinedButtonColors(
            containerColor = if (isSelected) CustomColor.SELECTED_BUTTON_BACKGROUND_COLOR.color else Color.Transparent,
            contentColor = if (isSelected) CustomColor.SELECTED_BUTTON_BORDER_AND_TEXT_COLOR.color else CustomColor.DEFAULT_TEXT_COLOR.color,
        ),
        border = BorderStroke(
            width = 2.dp,
            color = if (isSelected) CustomColor.SELECTED_BUTTON_BORDER_AND_TEXT_COLOR.color
            else CustomColor.TEXT_INPUT_DEFAULT_BORDER_COLOR.color,
        ),
    ) {
        Box(
            modifier = Modifier
                .width(200.dp),
            contentAlignment = alignment,
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun DefaultSelectButtonPreview() {
    Column {
        DefaultSelectButton(false, {}, content = { Text("Hello") })
        DefaultSelectButton(false, {}, content = { Text("조디악사무엘조디악사무엘조디악사무엘조디악사무엘조디악사무엘조디악사무엘조디악사무엘조디악사무엘조디악사무엘조디악사무엘조디악사무엘조디악사무엘") })
        DefaultSelectButton(false, {}, content = { Text("To Do") })
        DefaultSelectButton(false, {}, content = { Text("In Progress") })
        DefaultSelectButton(false, {}, content = { Profile("조디악") })
    }
}
