package woowacourse.kanban.board.component.taskmodal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.TaskState

@Composable
fun ModalBodySelector(
    title: String,
    essential: Boolean,
    items: List<String>,
    content: @Composable (item: String, id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        ModalInputTitle(
            title = title,
            essential = essential,
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 3),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            itemsIndexed(
                items,
            ) { index, item ->
                content(
                    item,
                    index,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ModalBodySelectorOptionPreview() {
    var selectedId by remember { mutableIntStateOf(value = 0) }

    ModalBodySelector(
        title = "상태",
        essential = true,
        items = TaskState.entries.map { state ->
            when (state) {
                TaskState.TODO -> "To Do"
                TaskState.IN_PROGRESS -> "In Progress"
                TaskState.DONE -> "Done"
            }
        },
        content = @Composable { name, id ->
            ModalOptionButton(
                isSelected = selectedId == id,
                selectedContainerColor = Color(0xFFEFF6FF),
                selectedBorderColor = Color(0xFF1447E6),
                onClick = {
                },
                content = {
                    Text(
                        text = name,
                    )
                },
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun ModalOptionAssigneePreview() {
    var selectedId by remember { mutableIntStateOf(value = 0) }
    val assignees = listOf(
        Assignee(name = "커비"),
        Assignee(name = "바드"),
        Assignee(name = "아오"),
        Assignee(name = "하로"),
    )
    ModalBodySelector(
        title = "담당자",
        essential = true,
        items = assignees.map { it.name },
        content = @Composable { name, id ->
            ModalOptionButton(
                isSelected = selectedId == id,
                selectedContainerColor = Color(0xFFEFF6FF),
                selectedBorderColor = Color(0xFF615FFF),
                onClick = {
                    selectedId = id
                },
                content = {
                    ModalOptionAssignee(
                        modifier = Modifier,
                        name = name,
                    )
                },
            )
        },
    )
}
