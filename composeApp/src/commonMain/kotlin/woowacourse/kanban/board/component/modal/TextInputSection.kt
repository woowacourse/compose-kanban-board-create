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
import woowacourse.kanban.board.model.TextInputState

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
    val titleInputState = TextInputState(
        value = title,
        onChange = { title = it },
        isError = isTitleEmpty
    )

    val descriptionInputState = TextInputState(
        value = description,
        onChange = { description = it }
    )

    val tagsInputState = TextInputState(
        value = tags,
        onChange = { tags = it },
        isError = isNotValidTag
    )

    Column {
        TextInput(
            label = ComponentText.TITLE_LABEL,
            value = titleInputState.value,
            placeholder = ComponentText.TITLE_PLACEHOLDER,
            onTextChange = titleInputState.onChange,
            isError = titleInputState.isError,
            errorText = ComponentText.TITLE_ERROR,
        )
        TextInput(
            label = ComponentText.DESCRIPTION_LABEL,
            value = descriptionInputState.value,
            singleLine = false,
            modifier = Modifier.height(200.dp),
            placeholder = ComponentText.DESCRIPTION_PLACEHOLDER,
            onTextChange = descriptionInputState.onChange,
        )
        TextInput(
            label = ComponentText.TAG_LABEL,
            value = tagsInputState.value,
            placeholder = ComponentText.TAG_PLACEHOLDER,
            onTextChange = tagsInputState.onChange,
            supportingText = ComponentText.TAG_SUPPORTING,
            isError = tagsInputState.isError,
            errorText = ComponentText.TAG_ERROR,
        )
    }
}
@Composable
fun TextInputSection(
    titleInputState: TextInputState,
    descriptionInputState: TextInputState,
    tagsInputState: TextInputState,
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
            value = titleInputState.value,
            placeholder = ComponentText.TITLE_PLACEHOLDER,
            onTextChange = titleInputState.onChange,
            isError = titleInputState.isError,
            errorText = ComponentText.TITLE_ERROR,
        )
        TextInput(
            label = ComponentText.DESCRIPTION_LABEL,
            value = descriptionInputState.value,
            singleLine = false,
            modifier = Modifier.height(200.dp),
            placeholder = ComponentText.DESCRIPTION_PLACEHOLDER,
            onTextChange = descriptionInputState.onChange,
        )
        TextInput(
            label = ComponentText.TAG_LABEL,
            value = tagsInputState.value,
            placeholder = ComponentText.TAG_PLACEHOLDER,
            onTextChange = tagsInputState.onChange,
            supportingText = ComponentText.TAG_SUPPORTING,
            isError = tagsInputState.isError,
            errorText = ComponentText.TAG_ERROR,
        )
    }
}
