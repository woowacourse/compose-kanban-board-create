package woowacourse.kanban.board.component.createtaskcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.constant.StateButtonConst
import woowacourse.kanban.board.constant.TitleConst

@Composable
fun LabelAndContent (
    label: String,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
){

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF364153),
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ){
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LabelAndStateButtonPreview() {

    val stateOptions = listOf(
        StateButtonConst.STATE_BUTTON_TODO,
        StateButtonConst.STATE_BUTTON_PROGRESS,
        StateButtonConst.STATE_BUTTON_DONE
    )
    var selectedState by remember { mutableStateOf(stateOptions[0]) }

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
}

@Preview(showBackground = true)
@Composable
fun LabelAndTextFieldPreview() {

    var title by remember { mutableStateOf("") }
    val isTitleEmpty by remember {
        derivedStateOf {
            title.isEmpty()
        }
    }

    LabelAndContent(
        label = TitleConst.TITLE_LABEL,
        modifier = Modifier,
    ) {
        TextInput(
            value = title,
            placeholder = TitleConst.TITLE_PLACEHOLDER,
            onTextChange = { title = it },
            singleLine = true,
            isError = isTitleEmpty,
            errorText = TitleConst.TITLE_ERROR
        )
    }
}