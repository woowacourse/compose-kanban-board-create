package woowacourse.kanban.newTaskCreate.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.cancel_button
import org.jetbrains.compose.resources.painterResource
import woowacourse.kanban.board.CustomColor

@Composable
fun TopBar(onclick: () -> Unit) {
    Row(
        modifier = Modifier
            .size(width = 672.dp, height = 85.dp)
            .background(Color.White)
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = 4.dp,
            ),
            text = "새 태스크 생성",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = CustomColor.Gray900,
        )
        IconButton(onClick = onclick) {
            Icon(
                painter = painterResource(Res.drawable.cancel_button),
                contentDescription = "cancel button",
                modifier = Modifier
                    .size(24.dp),
            )
        }
    }
}

@Preview
@Composable
private fun TopBarPreview() {
    TopBar(
        onclick = {},
    )
}