package woowacourse.kanban.board.component.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.ComponentText
import woowacourse.kanban.board.Validator

@Composable
fun TextInputSection(
    title: String,
    description: String,
    tags: String,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onTagsChange: (String) -> Unit,
    isTitleEmpty: Boolean,
    isNotValidTag: Boolean,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        TextInput(
            label = ComponentText.TITLE_LABEL,
            value = title,
            placeholder = ComponentText.TITLE_PLACEHOLDER,
            onTextChange = onTitleChange,
            isError = isTitleEmpty,
            errorText = ComponentText.TITLE_ERROR,
        )
        TextInput(
            label = ComponentText.DESCRIPTION_LABEL,
            value = description,
            singleLine = false,
            modifier = Modifier.height(200.dp),
            placeholder = ComponentText.DESCRIPTION_PLACEHOLDER,
            onTextChange = onDescriptionChange,
        )
        TextInput(
            label = ComponentText.TAG_LABEL,
            value = tags,
            placeholder = ComponentText.TAG_PLACEHOLDER,
            onTextChange = onTagsChange,
            supportingText = ComponentText.TAG_SUPPORTING,
            isError = isNotValidTag,
            errorText = ComponentText.TAG_ERROR,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TextInputSectionPreview() {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var tags by remember { mutableStateOf("") }

    val isTitleEmpty by remember {
        derivedStateOf {
            Validator.checkTitleIsEmpty(title)
        }
    }

    val isNotValidTag by remember {
        derivedStateOf {
            Validator.checkNotValidTags(tags)
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
        TextInput(
            label = ComponentText.DESCRIPTION_LABEL,
            value = description,
            singleLine = false,
            modifier = Modifier.height(200.dp),
            placeholder = ComponentText.DESCRIPTION_PLACEHOLDER,
            onTextChange = { description = it },
        )
        TextInput(
            label = ComponentText.TAG_LABEL,
            value = tags,
            placeholder = ComponentText.TAG_PLACEHOLDER,
            onTextChange = { tags = it },
            supportingText = ComponentText.TAG_SUPPORTING,
            isError = isNotValidTag,
            errorText = ComponentText.TAG_ERROR,
        )
    }
}