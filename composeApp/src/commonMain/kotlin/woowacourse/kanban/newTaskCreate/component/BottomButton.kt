package woowacourse.kanban.newTaskCreate.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomButton(
    text: String,
    textColor: Color,
    backgroundColor: Color,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(10.dp),
        enabled = enabled,
        modifier = modifier,
        colors = buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor,
        ),
    ) {
        Text(
            text = text,
            modifier = Modifier,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
            ),
        )
    }
}

@Preview
@Composable
private fun BottomButtonPreview() {
    Column {
        BottomButton(
            text = "생성",
            textColor = Color.White,
            backgroundColor = Color.Magenta,
            onClick = { },
            enabled = true,
        )

        BottomButton(
            text = "생성",
            textColor = Color.White,
            backgroundColor = Color.Magenta,
            onClick = { },
            enabled = false,
        )
    }
}