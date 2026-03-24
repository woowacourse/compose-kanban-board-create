package woowacourse.kanban.board.ui.taskCardForm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.design.Font

@Composable
fun TagInputSection(
    modifier: Modifier = Modifier,
    tags: String = "",
    onTagsChange: (String) -> Unit = {},
    errorMessage: String? = null,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "태그",
            fontSize = Font.FORMTITLE.size,
            fontWeight = Font.FORMTITLE.weight,
            modifier = Modifier.padding(8.dp),
        )
        TagInputField(
            tags = tags,
            onTagsChange = onTagsChange,
            errorMessage = errorMessage,
        )
    }
}

@Composable
private fun TagInputField(
    tags: String,
    onTagsChange: (String) -> Unit,
    errorMessage: String?,
    modifier: Modifier = Modifier
) {
    val isError = tags.isNotEmpty() && errorMessage != null
    val supportingText = errorMessage ?: "5자 이내의 태그를 최대 5개까지 등록할 수 있습니다."

    OutlinedTextField(
        value = tags,
        onValueChange = {
            onTagsChange(it)
        },
        textStyle = TextStyle(color = if (isError) MaterialTheme.colorScheme.error else Color.Black),
        isError = isError,
        placeholder = {
            Text(
                text = "태그를 쉼표로 구분하여 입력하세요(예: 버그, 긴급)",
                fontSize = Font.FORMINPUT.size,
                fontWeight = Font.FORMINPUT.weight,
                color = Color(0xFFAAAAAA),
            )
        },
        supportingText = {
            Text(
                text = supportingText,
                fontSize = Font.FORMEXPLAIN.size,
                fontWeight = Font.FORMEXPLAIN.weight,
            )
        },
        trailingIcon = {
            if (isError) {
                Icon(
                    imageVector = Icons.Filled.Error,
                    contentDescription = "error",
                    tint = MaterialTheme.colorScheme.error,
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
    )
}

@Preview(showBackground = true)
@Composable
private fun TagInputPreview() {
    MaterialTheme {
        TagInputSection(
            tags = "",
            onTagsChange = {},
        )
    }
}
