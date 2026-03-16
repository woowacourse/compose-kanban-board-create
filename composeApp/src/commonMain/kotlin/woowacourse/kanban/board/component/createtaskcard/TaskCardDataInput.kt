package woowacourse.kanban.board.component.createtaskcard

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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.constant.ColorPalette
import woowacourse.kanban.board.constant.HeaderAndFooterConst
import woowacourse.kanban.board.constant.TagsMaxValue

@Composable
fun TaskCardDataInput() {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var tags by remember { mutableStateOf("") }
    val isNotValidTitle by remember {
        derivedStateOf {
            title.isBlank()
        }
    }
    val isNotValidTags by remember {
        derivedStateOf {
            if(tags.isBlank()){
                return@derivedStateOf false
            }
            val extractTags = tags.split(",").map { it.trim() }

            extractTags.size > TagsMaxValue.MAX_TAGS ||
                    extractTags.any { tag ->
                tag.isEmpty() || tag.length > TagsMaxValue.TAG_MAX_TEXT_LENGTH
            }
        }
    }

    val stateOptions = listOf(
        HeaderAndFooterConst.STATE_BUTTON_TODO,
        HeaderAndFooterConst.STATE_BUTTON_PROGRESS,
        HeaderAndFooterConst.STATE_BUTTON_DONE
    )
    var selectedState by remember { mutableStateOf(stateOptions[0]) }

    val managerOptions = listOf(
        HeaderAndFooterConst.PROFILE_BUTTON_DINO,
        HeaderAndFooterConst.PROFILE_BUTTON_PAMES
    )
    var selectedManager by remember { mutableStateOf(managerOptions[0]) }

    Card(
        modifier = Modifier
            .width(800.dp)
            .padding(50.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp),
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
                        .clickable {},
                    imageVector = Icons.Default.Close,
                    contentDescription = "닫기",
                )
            }

            HorizontalDivider()

            LabelAndContent(
                label = HeaderAndFooterConst.TITLE_LABEL,
                modifier = Modifier,
            ) {
                TextInput(
                    value = title,
                    placeholder = HeaderAndFooterConst.TITLE_PLACEHOLDER,
                    onTextChange = { title = it },
                    singleLine = true,
                    isError = isNotValidTitle,
                    errorText = HeaderAndFooterConst.TITLE_ERROR
                )
            }

            LabelAndContent(
                label = HeaderAndFooterConst.DESCRIPTION_LABEL,
                modifier = Modifier,
            ) {
                TextInput(
                    value = description,
                    placeholder = HeaderAndFooterConst.DESCRIPTION_PLACEHOLDER,
                    onTextChange = { description = it },
                    modifier = Modifier.height(200.dp),
                )
            }

            LabelAndContent(
                label = HeaderAndFooterConst.TAG_LABEL,
                modifier = Modifier,
            ) {
                TextInput(
                    value = tags,
                    placeholder = HeaderAndFooterConst.TAG_PLACEHOLDER,
                    onTextChange = { tags = it },
                    modifier = Modifier,
                    singleLine = true,
                    supportingText = HeaderAndFooterConst.TAG_SUPPORTING,
                    isError = isNotValidTags,
                    errorText = HeaderAndFooterConst.TAG_ERROR,
                )
            }

            LabelAndContent(
                label = HeaderAndFooterConst.STATE_BUTTON_LABEL,
                modifier = Modifier,
            ) {
                stateOptions.forEach { option ->
                    StateButton(
                        option = option,
                        selectedState = selectedState,
                        onClick = { selectedState = it }
                    )
                }
            }

            LabelAndContent(
                label = HeaderAndFooterConst.PROFILE_BUTTON_LABEL,
                modifier = Modifier
            ) {
                managerOptions.forEach { manager ->
                    ManagerButton(
                        option = manager,
                        selectedOption = selectedManager,
                        onClick = { selectedManager = it }
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
            ) {
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    FooterButton(
                        containerColor = Color.Transparent,
                        contentColor = ColorPalette.Gray20,
                        text = HeaderAndFooterConst.CANCEL_BUTTON,
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    FooterButton(
                        enabled =  !isNotValidTitle && !isNotValidTags ,
                        containerColor = ColorPalette.Blue50,
                        text = HeaderAndFooterConst.CREATE_BUTTON,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 1000)
@Composable
private fun TaskCardDataInputPreview() {
    TaskCardDataInput()
}

//@Preview(showBackground = true)
@Composable
fun ButtonPreview(){
    val stateOptions = listOf(HeaderAndFooterConst.STATE_BUTTON_TODO, HeaderAndFooterConst.STATE_BUTTON_PROGRESS, HeaderAndFooterConst.STATE_BUTTON_DONE)
    var selectedState by remember { mutableStateOf(stateOptions[0]) }

    val managerOptions = listOf(HeaderAndFooterConst.PROFILE_BUTTON_DINO, HeaderAndFooterConst.PROFILE_BUTTON_PAMES)
    var selectedManager by remember { mutableStateOf(managerOptions[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ){
        LabelAndContent(
            label = HeaderAndFooterConst.STATE_BUTTON_LABEL,
            modifier = Modifier,
        ) {
            stateOptions.forEach { option ->
                StateButton(
                    option = option,
                    selectedState = selectedState,
                    onClick = { selectedState = it }
                )
            }
        }
        LabelAndContent(
            label = HeaderAndFooterConst.PROFILE_BUTTON_LABEL,
            modifier = Modifier
        ) {
            managerOptions.forEach { manager ->
                ManagerButton(
                    option = manager,
                    selectedOption = selectedManager,
                    onClick = { selectedManager = it }
                )
            }
        }
    }
}