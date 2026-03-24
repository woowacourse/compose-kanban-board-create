package woowacourse.kanban.newTaskCreate.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.CustomColor

@Composable
fun CreateNewTaskDialogBottom(
    isCreateEnabled: Boolean,
    onCreate : () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.End,
    ) {
        BottomButton(
            text = "취소",
            textColor = Color.Black,
            backgroundColor = Color.White,
            enabled = true,
            modifier = Modifier.testTag("cancel_create_task_button"),
            onClick = { },
        )
        Spacer(modifier = Modifier.width(12.dp))
        BottomButton(
            text = "생성",
            textColor = Color.White,
            backgroundColor = CustomColor.Violet600,
            enabled = isCreateEnabled,
            modifier = Modifier.testTag("create_task_button"),
            onClick = onCreate,
        )
    }
}


@Preview
@Composable
private fun CreateNewTaskDialogBottomPreview() {
    CreateNewTaskDialogBottom(
        isCreateEnabled = true,
        onCreate = {}
    )
}
