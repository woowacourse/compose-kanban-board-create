package woowacourse.kanban.board.createtaskcard.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import woowacourse.kanban.board.constant.ColorPalette
import woowacourse.kanban.board.constant.DescriptionConst
import woowacourse.kanban.board.constant.HeaderAndFooterConst
import woowacourse.kanban.board.constant.ManagerButtonConst
import woowacourse.kanban.board.constant.StateButtonConst
import woowacourse.kanban.board.constant.TagsConst
import woowacourse.kanban.board.constant.TestTags
import woowacourse.kanban.board.constant.TitleConst
import woowacourse.kanban.board.taskcard.domain.Manager
import woowacourse.kanban.board.taskcard.domain.State
import woowacourse.kanban.board.taskcard.domain.TaskCardData
import woowacourse.kanban.board.createtaskcard.domain.TaskCardDataInputState
import woowacourse.kanban.board.taskcard.domain.Tags
import woowacourse.kanban.board.taskcard.domain.Title
import woowacourse.kanban.board.taskcard.domain.value

@Composable
fun TaskCardDataInput(
    state: TaskCardDataInputState,
    onCreate: (TaskCardData) -> Unit,
    onCancel: () -> Unit
) {
    Dialog(
        onDismissRequest = onCancel,
        properties = DialogProperties(usePlatformDefaultWidth = false),

    ){
        Card(
            modifier = Modifier
                .width(800.dp)
                .padding(50.dp)
                .testTag(TestTags.MODAL_BODY),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 24.dp)
                    .testTag(TestTags.HEADER),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = HeaderAndFooterConst.HEADER_LABEL,
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    )
                    Icon(
                        modifier = Modifier
                            .clickable {
                                onCancel()
                            },
                        imageVector = Icons.Default.Close,
                        contentDescription = "닫기",
                    )
                }

                HorizontalDivider()

                LabelAndContent(
                    label = TitleConst.TITLE_LABEL,
                ) {
                    TextInput(
                        value = state.title,
                        placeholder = TitleConst.TITLE_PLACEHOLDER,
                        onTextChange = { state.title = it },
                        singleLine = true,
                        isError = Title(state.title).isNotValidTitle(),
                        errorText = TitleConst.TITLE_ERROR,
                        modifier = Modifier.testTag(TestTags.TITLE_INPUT)
                    )
                }

                LabelAndContent(
                    label = DescriptionConst.DESCRIPTION_LABEL,
                ) {
                    TextInput(
                        value = state.description,
                        placeholder = DescriptionConst.DESCRIPTION_PLACEHOLDER,
                        onTextChange = { state.description = it },
                        modifier = Modifier
                            .height(200.dp)
                            .testTag(TestTags.DESCRIPTION_INPUT),
                    )
                }

                LabelAndContent(
                    label = TagsConst.TAG_LABEL,
                ) {
                    TextInput(
                        value = state.tags,
                        placeholder = TagsConst.TAG_PLACEHOLDER,
                        onTextChange = { state.tags = it },
                        singleLine = true,
                        supportingText = TagsConst.TAG_SUPPORTING,
                        isError = Tags(state.tags).isNotValidTags(),
                        errorText = TagsConst.TAG_ERROR,
                        modifier = Modifier.testTag(TestTags.TAGS_INPUT)
                    )
                }

                LabelAndContent(
                    label = StateButtonConst.STATE_BUTTON_LABEL,
                    modifier = Modifier.testTag(TestTags.STATE_BTN),
                ) {
                    State.entries.forEach { option ->
                        StateButton(
                            option = option.value(),
                            isSelected = state.selectedState == option,
                            onClick = { state.selectedState = option },
                            modifier = Modifier.testTag("${option.value()}${TestTags.BTN}")
                        )
                    }
                }

                LabelAndContent(
                    label = ManagerButtonConst.MANAGER_BUTTON_LABEL,
                    modifier = Modifier.testTag(TestTags.MANAGER_BTN)
                ) {
                    Manager.entries.forEach { option ->
                        ManagerButton(
                            option = option.value(),
                            isSelected = state.selectedManager == option,
                            onClick = { state.selectedManager = option },
                            modifier = Modifier.testTag("${option.value()}${TestTags.BTN}"),
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .testTag(TestTags.FOOTER),
                ) {
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        ActionButton(
                            containerColor = Color.Transparent,
                            contentColor = ColorPalette.Gray20,
                            text = HeaderAndFooterConst.CANCEL_BUTTON,
                            onClick = {
                                onCancel()
                            }
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        ActionButton(
                            enabled =  !Title(state.title).isNotValidTitle() && !Tags(state.tags).isNotValidTags() ,
                            containerColor = ColorPalette.Blue50,
                            text = HeaderAndFooterConst.CREATE_BUTTON,
                            onClick = {
                                onCreate(state.getCard())
                                state.initializeState()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 1000)
@Composable
private fun TaskCardDataInputPreview() {
    val state = remember { TaskCardDataInputState() }

    TaskCardDataInput(
        state = state,
        onCreate = {
            print(it)
        },
        onCancel = {}
    )
}

//@Preview(showBackground = true)
@Composable
fun ButtonPreview(){
    val stateOptions = listOf(StateButtonConst.STATE_BUTTON_TODO, StateButtonConst.STATE_BUTTON_PROGRESS, StateButtonConst.STATE_BUTTON_DONE)
    var selectedState by remember { mutableStateOf(stateOptions[0]) }

    val managerOptions = listOf(ManagerButtonConst.MANAGER_BUTTON_DINO, ManagerButtonConst.MANAGER_BUTTON_PAMES)
    var selectedManager by remember { mutableStateOf(managerOptions[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ){
        LabelAndContent(
            label = StateButtonConst.STATE_BUTTON_LABEL,
            modifier = Modifier,
        ) {
            stateOptions.forEach { option ->
                StateButton(
                    option = option,
                    isSelected = selectedState == option,
                    onClick = { selectedState = option },
                )
            }
        }
        LabelAndContent(
            label = ManagerButtonConst.MANAGER_BUTTON_LABEL,
            modifier = Modifier
        ) {
            managerOptions.forEach { manager ->
                ManagerButton(
                    option = manager,
                    isSelected = selectedManager == manager,
                    onClick = { selectedManager = manager }
                )
            }
        }
    }
}
