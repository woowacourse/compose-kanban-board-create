package woowacourse.kanban.board.component.newTaskCreate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.CustomColor
import woowacourse.kanban.board.TaskStatusUIModel.Companion.toUIModel
import woowacourse.kanban.board.component.task.Profile
import woowacourse.kanban.board.data.Tags.Companion.validateTagsAndWordCount
import woowacourse.kanban.board.data.TaskStatus
import woowacourse.kanban.board.data.Title.Companion.validateTitle

@Composable
fun NewTaskForm(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    tags: String,
    onTagsChange: (String) -> Unit,
    selectedStatusIndex: Int,
    statusOptions: List<TaskStatus>,
    onStatusChange: (Int) -> Unit,
    selectedProfileIndex: Int,
    profileOptions: List<String>,
    onProfileChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth().verticalScroll(rememberScrollState()).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        DefaultTextField(
            value = title,
            text = "제목 *",
            onValueChange = onTitleChange,
            hintText = "태스크 제목을 입력하세요",
            defaultSupportingText = "",
            validate = {
                validateTitle(it)
            },
            modifier = Modifier.testTag("title_textField"),
        )
        DefaultTextField(
            value = description,
            text = "설명",
            onValueChange = onDescriptionChange,
            hintText = "태스크에 대한 자세한 설명을 입력하세요",
            defaultSupportingText = null,
            validate = { null },
            minLines = 4,
            maxLines = 5,
        )
        DefaultTextField(
            value = tags,
            text = "태그",
            onValueChange = onTagsChange,
            hintText = "태그를 쉼표로 구분하여 입력하세요 (예: 버그, 긴급)",
            defaultSupportingText = "5자 이내의 태그를 최대 5개까지 등록할 수 있습니다.",
            validate = { validateTagsAndWordCount(it) },
            modifier = Modifier.testTag("tag_textField"),
        )
        ItemSelectionFormBox(
            text = "상태 *",
        ) {
            statusOptions.forEachIndexed { index, status ->
                DefaultSelectButton(
                    isSelected = selectedStatusIndex == index,
                    onClick = { onStatusChange(index) },
                    content = { Text(status.toUIModel().text) },
                )
            }
        }
        ItemSelectionFormBox(
            text = "담당자 *",
        ) {
            profileOptions.forEachIndexed { index, nickname ->
                DefaultSelectButton(
                    isSelected = selectedProfileIndex == index,
                    onClick = { onProfileChange(index) },
                    content = {
                        Profile(nickname = nickname)
                    },
                )
            }
        }
    }
}

@Composable
private fun DefaultTextField(
    value: String,
    text: String,
    onValueChange: (String) -> Unit,
    hintText: String,
    defaultSupportingText: String?,
    validate: (inputValue: String) -> String?,
    modifier: Modifier = Modifier,
    minLines: Int = 1,
    maxLines: Int = 1,
) {
    var isDirty by remember { mutableStateOf(false) }

    val errorMessage = validate(value)
    val isError = (errorMessage != null)

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = text,
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(
                color = CustomColor.GRAY_TEXT_COLOR.color,
                fontWeight = FontWeight.Medium,
            ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                errorTextColor = CustomColor.TEXT_INPUT_ERROR_BORDER_COLOR.color,
                errorCursorColor = Color.Red,
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
                        Icons.Filled.Error,
                        "error",
                        tint = MaterialTheme.colorScheme.error,
                    )
            },
            supportingText = {
                if (isError && isDirty) Text(errorMessage) else Text(defaultSupportingText ?: "")
            },
            keyboardActions = KeyboardActions { validate(value) },
        )
    }
}

@Preview
@Composable
private fun DefaultTextFieldPreview(@PreviewParameter(DefaultTextFieldParameterProvider::class) text: String) {
    DefaultTextField(
        value = text,
        text = "hint",
        onValueChange = { validateTitle(it) },
        hintText = " ",
        defaultSupportingText = "",
        validate = { validateTitle("text") },
        minLines = 1,
        maxLines = 1,
    )
}

private class DefaultTextFieldParameterProvider() : PreviewParameterProvider<String> {
    override val values = sequenceOf<String>(
        "제목 *",
        "설명",
        "태그",
    )
}
