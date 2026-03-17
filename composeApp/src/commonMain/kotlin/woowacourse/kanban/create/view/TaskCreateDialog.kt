package woowacourse.kanban.create.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import woowacourse.kanban.create.model.TaskCreateViewModel
import woowacourse.kanban.create.view.createTextInput.CreateTextInput
import woowacourse.kanban.create.view.radioSelector.CoachButton
import woowacourse.kanban.create.view.radioSelector.RadioSelector
import woowacourse.kanban.create.view.radioSelector.StatusButton

@Composable
fun TaskCreateDialog(modifier: Modifier = Modifier, viewModel: TaskCreateViewModel) {
    Column(
        modifier = modifier.background(color = Color.White)
            .size(
                width = 672.dp,
                height = 900.dp,
            ),
    ) {
        DialogBar(
            modifier = Modifier.padding(
                vertical = 28.dp,
                horizontal = 24.dp,
            )
                .fillMaxWidth(),
        )
        HorizontalDivider()
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            CreateTextInput(
                title = "제목 *",
                placeHolder = "태스크 제목을 입력하세요",
                height = 48.dp,
                value = viewModel.titleInputValue,
                onChangeValue = { newTextValue ->
                    viewModel.onTitleChange(newTextValue)
                },
                isError = viewModel.isTitleError,
            )
            CreateTextInput(
                modifier = Modifier,
                title = "설명",
                placeHolder = "태스크에 대한 자세한 설명을 입력하세요",
                height = 116.dp,
                placeHolderAlignment = Alignment.TopStart,
                value = viewModel.contentInputValue,
                onChangeValue = { newTextValue -> viewModel.onContentChange(newTextValue) },
            )
            CreateTextInput(
                modifier = Modifier,
                title = "태그",
                placeHolder = "태그를 쉼표로 구분하여 입력하세요 (예: 버그, 긴급)",
                height = 44.dp,
                hintText = "5자 이내의 태그를 최대 5개까지 등록할 수 있습니다.",
                value = viewModel.tagInputValue,
                onChangeValue = { newTextValue ->
                    viewModel.onTagChange(newTextValue)
                },
                isError = viewModel.isTagError,
            )
            RadioSelector(
                header = "상태 *",
                items = viewModel.statuses,
            ) { index ->
                StatusButton(
                    status = viewModel.statuses[index],
                    isSelected = viewModel.selectedStatusIndex == index,
                    onClick = { viewModel.onStatusSelect(index) },
                    index = index,
                )
            }
            RadioSelector(
                header = "담당자 *",
                items = viewModel.names,
            ) { index ->
                CoachButton(
                    name = viewModel.names[index],
                    isSelected = viewModel.selectedCoachIndex == index,
                    onClick = { viewModel.onCoachSelect(index) },
                    index = index,
                )
            }
            HorizontalDivider()
            FooterRow(
                onCancel = { },
                onCreate = { viewModel.onCardCreate() },
                isCreateError = viewModel.isTitleError || viewModel.isTagError,
            )
        }
    }
}
