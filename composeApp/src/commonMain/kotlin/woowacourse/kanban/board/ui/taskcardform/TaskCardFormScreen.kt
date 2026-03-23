package woowacourse.kanban.board.ui.taskcardform

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.domain.TagError
import woowacourse.kanban.board.domain.Task
import woowacourse.kanban.board.domain.TaskState
import woowacourse.kanban.board.domain.TitleError
import woowacourse.kanban.board.ui.components.ActionButton
import woowacourse.kanban.board.ui.components.ActionButtonType
import woowacourse.kanban.board.ui.util.getTaskStateLabel
import woowacourse.kanban.board.util.parseByComma

@Composable
fun TaskCardFormScreen(
    modifier: Modifier = Modifier,
    onClosePanelClick: () -> Unit,
    onCreateCard: (Task) -> Unit,
) {
    val uiState: TaskCardFormState = rememberCardFormState()

    TaskCardFormContent(
        modifier = modifier,
        uiState = uiState,
        onCloseClick = onClosePanelClick,
        onCreateClick = {
            onCreateCard(
                Task.create(
                    title = uiState.taskTitle,
                    state = uiState.state,
                    managerName = uiState.managerName,
                    description = uiState.description,
                    tags = uiState.tempTags.parseByComma(),
                ),
            )
        },
    )
}

@Composable
fun TaskCardFormContent(
    uiState: TaskCardFormState,
    onCloseClick: () -> Unit,
    onCreateClick: () -> Unit,
    modifier: Modifier = Modifier,
    ) {
    OutlinedCard(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.background(Color.White).width(672.dp),
        ) {
            TaskCardFormHeaderSection(onCloseClick = onCloseClick)

            HorizontalDivider(modifier = Modifier.fillMaxWidth())

            TaskCardFormBodySection(uiState = uiState)

            HorizontalDivider(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp))

            TaskCardFormFooterSection(
                uiState = uiState,
                onCloseClick = onCloseClick,
                onCreateClick = onCreateClick,
            )
        }
    }
}

@Preview(showBackground = true, name = "태스크 카드 생성창 뷰", device = Devices.DESKTOP)
@Composable
private fun TaskCardFormContentPreview() {
    val uiState = rememberCardFormState()

    TaskCardFormContent(
        modifier = Modifier,
        uiState = uiState,
        onCloseClick = {},
        onCreateClick = {},
    )
}

@Composable
private fun TaskCardFormHeaderSection(
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(vertical = 28.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "새 태스크 생성",
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            lineHeight = 28.sp,
            letterSpacing = (-0.45).sp,
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = onCloseClick,
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "닫기 아이콘",
            )
        }
    }
}

@Composable
private fun TaskCardFormBodySection(
    uiState: TaskCardFormState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        TaskCardFormInputSection(
            title = "제목 *",
            placeholder = "태스크 제목을 입력하세요",
            value = uiState.taskTitle,
            onTextChange = {
                uiState.taskTitle = it
            },
            showAdditionalInfo = uiState.titleError == TitleError.EMPTY,
            infoText = "제목을 입력해 주세요.",
            isError = uiState.titleError == TitleError.EMPTY,
        )

        TaskCardFormInputSection(
            title = "설명",
            placeholder = "태스크에 대한 자세한 설명을 입력하세요",
            value = uiState.description,
            onTextChange = { uiState.description = it },
        )

        TaskCardFormInputSection(
            title = "태그",
            placeholder = "태그를 쉼표로 구분하여 입력하세요 (예: 버그, 긴급)",
            value = uiState.tempTags,
            onTextChange = {
                uiState.tempTags = it
            },
            showAdditionalInfo = true,
            infoText = uiState.tagInfoText,
            isError = uiState.tagError != TagError.NONE,
        )

        TaskCardFormStateSection(
            selectedState = uiState.state,
            onStateChange = { uiState.state = it },
        )

        TaskCardFormManagerSection(
            selectedManager = uiState.managerName,
            onManagerChange = { uiState.managerName = it },
        )
    }
}

@Composable
private fun TaskCardFormFooterSection(
    uiState: TaskCardFormState,
    onCloseClick: () -> Unit,
    onCreateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ActionButtonSection(
        modifier = modifier.padding(horizontal = 24.dp).padding(bottom = 24.dp),
        createEnabled = uiState.createEnabled,
        onCancelClick = onCloseClick,
        onCreateClick = onCreateClick,
    )
}

@Composable
private fun TaskCardFormInputSection(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    onTextChange: (String) -> Unit = {},
    showAdditionalInfo: Boolean = false,
    infoText: String = "",
    isError: Boolean = false,
) {
    val errorColor = Color(0xFFB3261E)
    Column(
        modifier = modifier,
    ) {
        TitleText(title)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = { onTextChange(it) },
            trailingIcon = {
                if (isError) Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = "에러 아이콘",
                    tint = errorColor,
                )
            },
            isError = isError,
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color(0xFFAAAAAA),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 1.sp,
                )
            },
            textStyle = TextStyle(
                color = if (isError) errorColor else Color.Black,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 1.sp,
            ),
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        )
        if (showAdditionalInfo) {
            Text(
                text = infoText,
                color = if (isError) errorColor else Color(0xFF49454F),
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 16.sp,
                modifier = Modifier.padding(top = 4.dp),
            )
        }
    }
}


@Composable
private fun TaskCardFormStateSection(
    selectedState: TaskState,
    onStateChange: (TaskState) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        TitleText("상태 *")
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            StateButton(
                text = TaskState.TO_DO.getTaskStateLabel(),
                isSelected = selectedState == TaskState.TO_DO,
                onClick = { onStateChange(TaskState.TO_DO) },
                modifier = Modifier.width(200.dp).height(52.dp),
            )
            StateButton(
                text = TaskState.IN_PROGRESS.getTaskStateLabel(),
                isSelected = selectedState == TaskState.IN_PROGRESS,
                onClick = { onStateChange(TaskState.IN_PROGRESS) },
                modifier = Modifier.width(200.dp).height(52.dp),
            )
            StateButton(
                text = TaskState.DONE.getTaskStateLabel(),
                isSelected = selectedState == TaskState.DONE,
                onClick = { onStateChange(TaskState.DONE) },
                modifier = Modifier.width(200.dp).height(52.dp),
            )
        }
    }
}

@Composable
private fun StateButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val containerColor = if (isSelected) Color(0xFFE5E7EB) else Color.White
    val contentColor = if (isSelected) Color(0xFF1447E6) else Color.Black
    val borderColor = if (isSelected) Color(0xFF1447E6) else Color(0xFFE5E7EB)

    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(20),
        border = BorderStroke(1.dp, borderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        modifier = modifier,
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = (-0.3).sp,
            lineHeight = 24.sp,
        )
    }
}

@Composable
private fun TaskCardFormManagerSection(
    selectedManager: String,
    onManagerChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        TitleText("담당자 *")
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            ManagerButton(
                text = "다이노",
                isSelected = selectedManager == "다이노",
                onClick = { onManagerChange("다이노") },
                modifier = Modifier.width(200.dp).height(68.dp),
            )
            ManagerButton(
                text = "페임스",
                isSelected = selectedManager == "페임스",
                onClick = { onManagerChange("페임스") },
                modifier = Modifier.width(200.dp).height(68.dp),
            )
        }
    }
}

@Composable
private fun ManagerButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val containerColor = if (isSelected) Color(0xFFE5E7EB) else Color.White
    val contentColor = if (isSelected) Color(0xFF1447E6) else Color.Black
    val borderColor = if (isSelected) Color(0xFF1447E6) else Color(0xFFE5E7EB)

    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(20),
        border = BorderStroke(1.dp, borderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "매니저 아이콘",
                modifier = Modifier.size(24.dp),
                tint = Color(0xFF838383),
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = (-0.15).sp,
                lineHeight = 20.sp,
            )
        }
    }
}

@Composable
private fun ActionButtonSection(
    createEnabled: Boolean,
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit = {},
    onCreateClick: () -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ActionButton(
            buttonText = "취소",
            buttonType = ActionButtonType.SECONDARY,
            enabled = true,
            onClick = { onCancelClick() },
        )
        Spacer(modifier = Modifier.width(12.dp))
        ActionButton(
            buttonText = "생성",
            buttonType = ActionButtonType.PRIMARY,
            enabled = createEnabled,
            onClick = {
                onCancelClick()
                onCreateClick()
            },
        )
    }
}

@Composable
private fun TitleText(
    title: String,
    modifier: Modifier  = Modifier
) {
    Text(
        text = title,
        modifier = modifier,
        fontSize = 14.sp,
        color = Color(0xFF364153),
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp,
        letterSpacing = 0.15.sp,
    )
}

@Composable
fun rememberCardFormState(): TaskCardFormState = remember { TaskCardFormState() }
