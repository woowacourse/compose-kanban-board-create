package woowacourse.kanban.board.component.createtaskcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.icon
import org.jetbrains.compose.resources.painterResource
import woowacourse.kanban.board.constant.ColorPalette
import woowacourse.kanban.board.constant.TitleConst

@Composable
fun TextInput(
    value: String,
    placeholder: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    supportingText: String? = null,
    isError: Boolean = false,
    errorText: String = "",
) {
    val borderColor = if (isError) ColorPalette.Red50 else ColorPalette.Gray70
    val textColor = if (isError) ColorPalette.Red50 else ColorPalette.Gray20

    OutlinedTextField(
        value = value,
        modifier = modifier
            .fillMaxWidth(),
        singleLine = singleLine,
        placeholder = {
            Text(
                text = placeholder,
                color = Color(0xFFAAAAAA),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
            )
        },
        trailingIcon = {
            if (isError) {
                Image(
                    painter = painterResource(Res.drawable.icon),
                    contentDescription = "에러 아이콘",
                    modifier = Modifier.size(20.dp),
                )
            }
        },
        supportingText = {
            if (isError) {
                Text(
                    text = errorText,
                    color = textColor,
                )
            } else if (supportingText != null) {
                Text(text = supportingText)
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = borderColor,
            focusedBorderColor = borderColor,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedTextColor = textColor,
            focusedTextColor = textColor,
        ),
        onValueChange = onTextChange,
    )
}

@Preview(showBackground = true)
@Composable
private fun TextInputPreview() {
    var title by remember { mutableStateOf("") }

    val isTitleEmpty by remember {
        derivedStateOf {
            title.isBlank()
        }
    }

    Column {
        TextInput(
            value = title,
            placeholder = TitleConst.TITLE_PLACEHOLDER,
            onTextChange = { title = it },
            isError = isTitleEmpty,
            errorText = TitleConst.TITLE_ERROR,
        )
    }
}