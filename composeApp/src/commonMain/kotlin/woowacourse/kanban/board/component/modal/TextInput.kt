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
import woowacourse.kanban.board.Gray20
import woowacourse.kanban.board.Gray40
import woowacourse.kanban.board.Gray70
import woowacourse.kanban.board.Red50
import woowacourse.kanban.board.model.TextInputValue
import woowacourse.kanban.board.model.modal.Title

@Preview(showBackground = true)
@Composable
private fun TextInputPreview() {
    var title by remember { mutableStateOf("") }

    val isTitleValid by remember {
        derivedStateOf {
            Title.isTitleValid(title)
        }
    }

    Column {
        TextInput(
            textInputValue = TextInputValue.TITLE,
            value = title,
            onTextChange = { title = it },
            isError = isTitleValid.not(),
        )
    }
}

@Composable
fun TextInput(
    textInputValue: TextInputValue,
    value: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    supportingText: String? = null,
    isError: Boolean = false,
) {
    val borderColor = if (isError) Red50 else Gray70
    val textColor = if (isError) Red50 else Gray20

    Column(
        modifier = modifier
    ) {
        Text(
            text = textInputValue.label,
            fontSize = 14.sp,
            color = Gray20,
            fontWeight = FontWeight.Bold,
        )
        OutlinedTextField(
            value = value,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            singleLine = singleLine,
            placeholder = {
                Text(
                    text = textInputValue.placeholder,
                    color = Gray40,
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
                        text = textInputValue.errorText,
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
