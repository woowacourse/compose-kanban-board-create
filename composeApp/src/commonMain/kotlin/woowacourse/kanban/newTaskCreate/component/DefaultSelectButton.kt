package woowacourse.kanban.newTaskCreate.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.CustomColor

@Composable
fun DefaultSelectButton(
    isSelected: Boolean,
    width: Dp,
    height: Dp,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .size(width, height)
            .background(Color.White),
        colors = outlinedButtonColors(
            containerColor = if (isSelected) CustomColor.Blue50 else Color.Transparent,
            contentColor = if (isSelected) CustomColor.Blue700 else CustomColor.Gray700,
        ),
        border = BorderStroke(
            width = 2.dp,
            color = if (isSelected) CustomColor.Blue700
            else CustomColor.Gray400,
        ),
    ) {
        Box(
            modifier = Modifier
                .width(200.dp),
        ) {
            content()
        }
    }
}

@Preview
@Composable
fun DefaultSelectButtonPreview() {
    Column {
        DefaultSelectButton(
            isSelected = false,
            width = 200.dp,
            height = 52.dp,
            onClick = {},
            content = { Text("Hello", modifier = Modifier.align(Alignment.CenterStart)) },
        )
        DefaultSelectButton(
            isSelected = false,
            width = 200.dp,
            height = 52.dp,
            onClick = {},
            content = { Text("조디악사무엘조디악사무엘조디악사무엘조디악사무엘조디악사무엘조디악사무엘조디악사무엘조디악사무엘조디악사무엘조디악사무엘조디악사무엘조디악사무엘") },
        )
        DefaultSelectButton(
            isSelected = false,
            width = 200.dp,
            height = 52.dp,
            onClick = {},
            content = { Text("To Do", modifier = Modifier.align(Alignment.Center)) },
        )
        DefaultSelectButton(
            isSelected = false,
            width = 200.dp,
            height = 52.dp,
            onClick = {},
            content = { Text("In Progress") },
        )
        DefaultSelectButton(
            isSelected = false,
            width = 200.dp,
            height = 68.dp,
            onClick = {},
            content = {
                ProfileCard(
                    "조디악",
                    Modifier.align(Alignment.CenterStart),
                )
            },
        )
    }
}
