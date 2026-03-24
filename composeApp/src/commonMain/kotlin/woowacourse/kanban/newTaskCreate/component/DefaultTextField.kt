package woowacourse.kanban.newTaskCreate.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.CustomColor


@Composable
fun DefaultTextField(
    modifier: Modifier,
    textFieldTag: String,
    titleText: String,
    value: String,
    onValueChange: (String) -> Unit,
    hintText: String,
    defaultSupportingText: String?,
    validate: (inputValue: String) -> String?,
    minLines: Int,
    maxLines: Int,
) {
    // 텍스트 필드에 값이 입력되어 있는지
    var isDirty by remember { mutableStateOf(false) }

    val errorMessage = validate(value)
    val isError = (errorMessage != null)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White),
    ) {
        Text(
            text = titleText,
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (defaultSupportingText.isNullOrBlank()) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(textFieldTag),
                value = value,
                textStyle = TextStyle(
                    color = CustomColor.Gray950,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    errorTextColor = CustomColor.Red700,
                ),
                placeholder = {
                    if (isError && isDirty) {
                        Text(errorMessage)
                    } else {
                        Text(hintText)
                    }
                },
                onValueChange = {
                    onValueChange(it)
                    isDirty = true
                },
                singleLine = false,
                minLines = minLines,
                maxLines = maxLines,
                isError = (isError && isDirty),
                trailingIcon = {
                    if (isError && isDirty)
                        Icon(
                            imageVector = Icons.Filled.Error,
                            contentDescription = "error_icon",
                            tint = MaterialTheme.colorScheme.error,
                        )
                },
                keyboardActions = KeyboardActions { validate(value) },
            )
        } else {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(textFieldTag),
                value = value,
                textStyle = TextStyle(
                    color = CustomColor.Gray950,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedPlaceholderColor = CustomColor.Gray400,
                    unfocusedContainerColor = Color.White,
                    errorTextColor = CustomColor.Red700,
                ),
                placeholder = { Text(hintText) },
                onValueChange = {
                    onValueChange(it)
                    isDirty = true
                },
                singleLine = false,
                minLines = minLines,
                maxLines = maxLines,
                isError = (isError && isDirty),
                trailingIcon = {
                    if (isError && isDirty)
                        Icon(
                            imageVector = Icons.Filled.Error,
                            contentDescription = "error_icon",
                            tint = MaterialTheme.colorScheme.error,
                        )
                },
                supportingText = {
                    if (isError && isDirty)
                        Text(errorMessage)
                    else if (!defaultSupportingText.isNullOrBlank())
                        Text(defaultSupportingText)
                },
                keyboardActions = KeyboardActions { validate(value) },
            )
        }

    }
}

private class DefaultTitleTextParameterProvider() : PreviewParameterProvider<String> {
    override val values = sequenceOf<String>(
        "제목 *",
        "설명",
        "태그",
    )
}

data class TextFieldPreviewCase(
    val title: String,
    val hint: String,
)

private class DefaultTextFieldPreviewPrvoider() : PreviewParameterProvider<TextFieldPreviewCase> {
    override val values = sequenceOf(
        TextFieldPreviewCase(
            "제목 * ",
            "태스크 제목을 입력하세요",
        ),
        TextFieldPreviewCase(
            "설명",
            "태크스에 대한 자세한 설명을 입력하세요",
        ),
        TextFieldPreviewCase(
            "태그",
            "태그를 쉼표로 구분하여 입력하세요 (예: 버그, 긴급)",
        ),
    )
}

@Preview
@Composable
private fun DefaultTextFieldPreview(
    @PreviewParameter(DefaultTextFieldPreviewPrvoider::class) item: TextFieldPreviewCase,
) {
    DefaultTextField(
        modifier = Modifier.fillMaxWidth(),
        textFieldTag = "preview_textField",
        titleText = item.title,
        value = "",
        onValueChange = {},
        hintText = item.hint,
        defaultSupportingText = "",
        validate = {
            validateTitle(
                it,
            )?.message()
        },
        minLines = 1,
        maxLines = 1,
    )
}
