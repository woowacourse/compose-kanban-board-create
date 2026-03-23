package woowacourse.kanban.board.ui.taskForm

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.regex.Pattern
import woowacourse.kanban.board.model.Tag
import woowacourse.kanban.board.util.ColorPalette
import woowacourse.kanban.board.util.Font
import woowacourse.kanban.board.util.Text as UiText

@Composable
fun TagInputSection(onTagsChange: (String) -> Unit = {}, onErrorChange: (Boolean) -> Unit = {}) {
    Column {
        Text(
            text = UiText.LABEL_TAG,
            style = Font.FORM_TITLE,
            modifier = Modifier.padding(8.dp),
        )
        TagInputField(
            onTagsChange = onTagsChange,
            onErrorChange = onErrorChange,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TagInputPreview() {
    MaterialTheme {
        TagInputSection(
            onTagsChange = {},
            onErrorChange = {},
        )
    }
}

@Composable
private fun TagInputField(onTagsChange: (String) -> Unit, onErrorChange: (Boolean) -> Unit) {
    var tags: String by remember { mutableStateOf("") }
    val tagsPattern = remember {
        Pattern.compile("^[^,]+(\\s*,\\s*[^,]+)*\$")
    }

    val isFormError = tags.isNotEmpty() && !tagsPattern.matcher(tags).matches()

    val isCountError = run {
        val splitTags = tags.split(",")
        tags.isNotEmpty() &&
            (
                splitTags.size > Tag.MAXIMUM_TAG_COUNT ||
                    !splitTags.all { it.trim().length in 1..Tag.MAXIMUM_TAG_LENGTH }
                )
    }

    val supportingText = if (isFormError) {
        UiText.ERROR_TAG_FORMAT_INVALID
    } else {
        UiText.HELPER_TAG_LIMIT
    }

    LaunchedEffect(isFormError, isCountError) {
        onErrorChange(isFormError || isCountError)
    }

    OutlinedTextField(
        value = tags,
        onValueChange = {
            tags = it
            onTagsChange(it)
        },
        textStyle = TextStyle(
            color = if (isFormError ||
                isCountError
            ) ColorPalette.Error else Color.Black,
        ),
        isError = isFormError || isCountError,
        placeholder = {
            Text(
                text = UiText.PLACEHOLDER_TAG,
                style = Font.FORM_INPUT,
                color = ColorPalette.PlaceHolder,
            )
        },
        supportingText = {
            Text(
                text = supportingText,
                style = Font.FORM_EXPLAIN,
            )
        },
        trailingIcon = {
            if (isFormError || isCountError) {
                Icon(
                    Icons.Filled.Error, UiText.CONTENT_ERROR, tint = ColorPalette.Error,
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
    )
}
