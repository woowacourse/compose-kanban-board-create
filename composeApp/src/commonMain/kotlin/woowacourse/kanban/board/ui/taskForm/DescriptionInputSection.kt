package woowacourse.kanban.board.ui.taskForm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.util.Font
import woowacourse.kanban.board.util.Text as UiText

@Composable
fun DescriptionInputSection(description: String, onDescriptionChange: (String) -> Unit = {}) {
    Column {
        Text(
            text = UiText.LABEL_DESCRIPTION,
            style = Font.FORM_TITLE,
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
        )
        DescriptionInputField(
            description = description,
            onDescriptionChange = onDescriptionChange,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DescriptionInputPreview() {
    MaterialTheme {
        var description: String by remember { mutableStateOf("") }
        DescriptionInputSection(
            description = description,
            onDescriptionChange = { description = it },
        )
    }
}

@Composable
private fun DescriptionInputField(description: String, onDescriptionChange: (String) -> Unit) {
    OutlinedTextField(
        value = description,
        onValueChange = {
            onDescriptionChange(it)
        },
        placeholder = {
            Text(
                text = UiText.PLACEHOLDER_DESCRIPTION,
                style = Font.FORM_INPUT,
                color = Color(0xFFAAAAAA),
            )
        },
        modifier = Modifier.height(150.dp).fillMaxWidth(),
    )
}
