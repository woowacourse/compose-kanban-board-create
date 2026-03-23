package woowacourse.kanban.board.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun ActionButton(
    buttonText: String,
    buttonType: ActionButtonType,
    enabled: Boolean,
    onClick: () -> Unit = {},
) {
    val contentColor = if (buttonType == ActionButtonType.PRIMARY) Color.White else Color(0xFF364153)
    val buttonColor = if (buttonType == ActionButtonType.PRIMARY) Color(0xFF4F39F6) else Color.White

    Button(
        onClick = { onClick() },
        enabled = enabled,
        modifier = Modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = contentColor,
        ),
        shape = RoundedCornerShape(20),
    ) {
        Text(
            text = buttonText,
            color = contentColor,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            letterSpacing = (-0.3).sp,
            lineHeight = 24.sp,
        )
    }
}


enum class ActionButtonType { PRIMARY, SECONDARY }


@Preview(showBackground = true, name = "PRIMARY 활성화 버튼")
@Composable
private fun MainButtonPreview() {
    ActionButton(
        buttonText = "메인 버튼",
        buttonType = ActionButtonType.PRIMARY,
        enabled = true,
    )
}

@Preview(showBackground = true, name = "Secondary 비활성화 버튼")
@Composable
private fun SubButtonPreview() {
    ActionButton(
        buttonText = "서브 버튼",
        buttonType = ActionButtonType.SECONDARY,
        enabled = false,
    )
}
