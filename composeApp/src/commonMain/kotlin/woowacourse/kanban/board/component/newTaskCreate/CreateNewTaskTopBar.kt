package woowacourse.kanban.board.component.newTaskCreate

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.cancel_button
import org.jetbrains.compose.resources.painterResource

@Composable
fun TopBar(onClickCloseButton: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "새 태스크 생성",
            modifier = Modifier.padding(
                horizontal = 4.dp,
            ),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
        )
        Icon(
            painter = painterResource(Res.drawable.cancel_button),
            contentDescription = "cancel button",
            modifier = Modifier
                .size(24.dp)
                .clickable(
                    onClick = onClickCloseButton,
                ),
        )
    }
}

@Preview
@Composable
private fun TopBarPreview() {
    TopBar({ })
}
