package woowacourse.kanban.board.component.taskmodal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ModalAction(
    onClickCancel: () -> Unit,
    onClickConfirm: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier,
) {
    HorizontalDivider(
        thickness = Dp.Hairline,
        color = Color.LightGray,
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.End,
    ) {
        Button(
            contentPadding = PaddingValues(all = 0.dp),
            shape = RoundedCornerShape(size = 10.dp),
            onClick = { onClickCancel() },
            colors = ButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.Black,
            ),
            modifier = Modifier
                .height(44.dp)
                .width(68.dp),
        ) {
            Text(
                text = "취소",
                fontSize = 16.sp,
            )
        }

        Spacer(
            modifier = Modifier.width(12.dp),
        )

        Button(
            enabled = enabled,
            contentPadding = PaddingValues(all = 0.dp),
            shape = RoundedCornerShape(size = 10.dp),
            onClick = { onClickConfirm() },
            colors = ButtonColors(
                containerColor = Color.Blue,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White,
            ),
            modifier = Modifier
                .height(44.dp)
                .width(68.dp),
        ) {
            Text(
                text = "생성",
                fontSize = 16.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ModalActionPreview() {
    Box(modifier = Modifier.padding(all = 10.dp)) {
        ModalAction(
            enabled = false,
            onClickCancel = {},
            onClickConfirm = {},
        )
    }
}
