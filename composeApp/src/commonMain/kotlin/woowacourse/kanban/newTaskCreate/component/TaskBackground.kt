package woowacourse.kanban.board.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.data.KanbanBoardSampleData
import woowacourse.kanban.newTaskCreate.data.Task
import woowacourse.kanban.ui.TaskUIMapper

@Composable
fun TaskBackground(generateTask: @Composable () -> Unit) {
    FlowRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(38.dp),
        maxItemsInEachRow = 4,
        horizontalArrangement = Arrangement.spacedBy(52.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        generateTask()
    }
}

@Composable
@Preview
fun TaskBackgroundPreview() {
    val data = listOf(Task(taskTitle = "조디악", assigneeId = "dino"))
    val assigneeById = KanbanBoardSampleData.assignees.associate { it.id to it.nickname }
    TaskBackground { TaskUIMapper().createTaskUI(data, assigneeById) }
}
