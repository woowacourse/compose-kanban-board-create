package woowacourse.kanban.board.component.modal

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
import woowacourse.kanban.board.ComponentText
import woowacourse.kanban.board.Gray20
import woowacourse.kanban.board.Gray70
import woowacourse.kanban.board.Red50
import woowacourse.kanban.board.Validator

@Composable
fun TextInput(
    label: String,
    value: String,
    placeholder: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    supportingText: String? = null,
    isError: Boolean = false,
    errorText: String = "",
) {
    val borderColor = if (isError) Red50 else Gray70
    val textColor = if (isError) Red50 else Gray20

    Column {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color(0xFF364153),
            fontWeight = FontWeight.Bold,
        )
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
}

@Preview(showBackground = true)
@Composable
private fun TextInputPreview() {
    var title by remember { mutableStateOf("") }

    val isTitleEmpty by remember {
        derivedStateOf {
            Validator.checkTitleIsEmpty(title)
        }
    }

    Column {
        TextInput(
            label = ComponentText.TITLE_LABEL,
            value = title,
            placeholder = ComponentText.TITLE_PLACEHOLDER,
            onTextChange = { title = it },
            isError = isTitleEmpty,
            errorText = ComponentText.TITLE_ERROR,
        )
    }
}