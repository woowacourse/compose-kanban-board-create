package woowacourse.kanban.board.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import woowacourse.kanban.board.ui.preview.KanbanPreview
import woowacourse.kanban.board.ui.theme.Gray
import woowacourse.kanban.board.ui.theme.Gray500
import woowacourse.kanban.board.ui.theme.Gray800
import woowacourse.kanban.board.ui.theme.Red

@Composable
fun SingleLineTextField(
    value: String,
    placeHolderMessage: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String? = null,
    supportingText: String? = null,
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = true,
        placeholder = {
            Text(
                text = placeHolderMessage,
                color = Gray,
            )
        },
        supportingText = {
            val text = if (isError) {
                errorMessage
            } else {
                supportingText
            }
            text?.let {
                Text(
                    text = it,
                    color = if (isError) Red else Gray800,
                )
            }
        },
        trailingIcon = {
            if (isError) {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = "error",
                    tint = Red,
                )
            }
        },
        isError = isError,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            unfocusedBorderColor = Gray500,
            errorBorderColor = Red,
        ),
    )
}

@Composable
@KanbanPreview
private fun SingleLineTextFieldPreview() {
    SingleLineTextField(
        placeHolderMessage = "제목을 입력해주세요.",
        isError = false,
        errorMessage = "제목을 입력해주세요.",
        value = "",
        onValueChange = { print(it) },
    )
}
