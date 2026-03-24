package woowacourse.kanban.newTaskCreate.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import woowacourse.kanban.newTaskCreate.data.Assignee
import woowacourse.kanban.newTaskCreate.data.TaskStatus

@Composable
fun <T> ItemSelectionFormBox(
    text: String, //제목, 상태 등등..
    items: List<T>,
    selectedItem: T,
    onItemSelected: (T) -> Unit,
    width: Dp,
    height: Dp,
    itemTestTag: ((T) -> String)? = null,
    itemContent: @Composable BoxScope.(T) -> Unit,
) {
    Column {
        Text(text = text)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items.forEach { item ->
                val buttonModifier = itemTestTag?.let { Modifier.testTag(it(item)) } ?: Modifier
                DefaultSelectButton(
                    isSelected = (selectedItem == item),
                    width = width,
                    height = height,
                    modifier = buttonModifier,
                    onClick = { onItemSelected(item) },
                ) {
                    itemContent(item)
                }
            }
        }
    }
}

@Preview(widthDp = 672)
@Composable
private fun ItemSelectionFormBoxPreview() {

    val statusItems = TaskStatus.entries
    val assigneeItems = listOf(
        Assignee(id = "dino", nickname = "다이노"),
        Assignee(id = "fames", nickname = "페임스"),
    )

    val elevation: ButtonElevation? = ButtonDefaults.buttonElevation(
        defaultElevation = 10.dp,
        pressedElevation = 0.dp,
        disabledElevation = 0.dp,
    )
    Column {
        ItemSelectionFormBox(
            text = "상태 *",
            items = statusItems,
            selectedItem = TaskStatus.TO_DO,
            onItemSelected = { },
            width = 200.dp,
            height = 52.dp,
        ) { status ->
            Text(status.name, modifier = Modifier.align(Alignment.Center))
        }
        ItemSelectionFormBox(
            text = "담당자",
            items = assigneeItems,
            selectedItem = assigneeItems.first(),
            onItemSelected = { },
            width = 200.dp,
            height = 68.dp,
        ) { assignee ->
            ProfileCard(
                nickname = assignee.nickname,
                modifier = Modifier.align(Alignment.CenterStart),
            )
        }
    }
}
