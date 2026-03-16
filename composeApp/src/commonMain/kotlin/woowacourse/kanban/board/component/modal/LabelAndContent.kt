package woowacourse.kanban.board.component.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.Blue50
import woowacourse.kanban.board.Blue80
import woowacourse.kanban.board.ComponentText.STATE_BUTTON_DONE
import woowacourse.kanban.board.ComponentText.STATE_BUTTON_LABEL
import woowacourse.kanban.board.ComponentText.STATE_BUTTON_PROGRESS
import woowacourse.kanban.board.ComponentText.STATE_BUTTON_TODO
import woowacourse.kanban.board.ComponentText.TITLE_ERROR
import woowacourse.kanban.board.ComponentText.TITLE_LABEL
import woowacourse.kanban.board.ComponentText.TITLE_PLACEHOLDER
import woowacourse.kanban.board.Gray20
import woowacourse.kanban.board.Gray70
import woowacourse.kanban.board.Validator

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
            modifier = modifier.fillMaxWidth(),
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

    val stateOptions = listOf(STATE_BUTTON_TODO, STATE_BUTTON_PROGRESS, STATE_BUTTON_DONE)
    var selectedState by remember { mutableStateOf(stateOptions[0]) }

    LabelAndContent(
        label = STATE_BUTTON_LABEL,
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
}

@Preview(showBackground = true)
@Composable
fun LabelAndTextFieldPreview() {

    var title by remember { mutableStateOf("") }
    val isTitleEmpty by remember {
        derivedStateOf {
            Validator.checkTitleIsEmpty(title)
        }
    }

    LabelAndContent(
        label = TITLE_LABEL,
        modifier = Modifier,
    ) {
        TextInput(
            value = title,
            placeholder = TITLE_PLACEHOLDER,
            onTextChange = { title = it },
            singleLine = true,
            isError = isTitleEmpty,
            errorText = TITLE_ERROR
        )
    }
}