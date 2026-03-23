package woowacourse.kanban.board.ui.taskForm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.util.Font
import woowacourse.kanban.board.util.Text as UiText

@Composable
fun TitleInputSection(title: String, onTitleChange: (String) -> Unit = {}, onErrorChange: (Boolean) -> Unit = {}) {
    Column {
        Text(
            text = UiText.LABEL_TITLE,
            style = Font.FORM_TITLE,
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
        )
        TitleInputField(
            title = title,
            onTitleChange = onTitleChange,
            onErrorChange = onErrorChange,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TitleInputPreview() {
    MaterialTheme {
        var title: String by remember { mutableStateOf("") }
        TitleInputSection(
            title = title,
            onTitleChange = { title = it },
            onErrorChange = {},
        )
    }
}

@Composable
private fun TitleInputField(title: String, onTitleChange: (String) -> Unit, onErrorChange: (Boolean) -> Unit) {
    var isEmptyError by remember { mutableStateOf(false) }
    val supportingText = if (isEmptyError) UiText.ERROR_TITLE_EMPTY_INPUT else ""
    OutlinedTextField(
        value = title,
        onValueChange = {
            onTitleChange(it)
        },
        isError = isEmptyError,
        placeholder = {
            Text(
                text = UiText.PLACEHOLDER_TITLE,
                style = Font.FORM_INPUT,
                color = Color(0xFFAAAAAA),
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                isEmptyError = !focusState.isFocused && title.isEmpty()
                onErrorChange(isEmptyError)
            },
        supportingText = {
            Text(
                text = supportingText,
                style = Font.FORM_EXPLAIN,
            )
        },

    )
}
