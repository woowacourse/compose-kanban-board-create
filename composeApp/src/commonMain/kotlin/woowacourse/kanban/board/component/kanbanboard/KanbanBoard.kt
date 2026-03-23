package woowacourse.kanban.board.component.kanbanboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.TaskStatusUIModel.Companion.toUIModel
import woowacourse.kanban.board.data.TaskStatus
import woowacourse.kanban.board.data.Tasks
import woowacourse.kanban.board.tasksExample

@Composable
fun KanbanBoard(tasks: Tasks) {
    FlowRow(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxHeight(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        TaskStatusCardHolder(
            tasks = tasks.filterTasksByStatus(TaskStatus.TO_DO),
            tasksSize = tasks.filterTasksByStatus(TaskStatus.TO_DO).size,
            tasksStatusUIModel = TaskStatus.TO_DO.toUIModel(),
        )

        TaskStatusCardHolder(
            tasks = tasks.filterTasksByStatus(TaskStatus.IN_PROGRESS),
            tasksSize = tasks.filterTasksByStatus(TaskStatus.IN_PROGRESS).size,
            tasksStatusUIModel = TaskStatus.IN_PROGRESS.toUIModel(),
        )

        TaskStatusCardHolder(
            tasks = tasks.filterTasksByStatus(TaskStatus.DONE),
            tasksSize = tasks.filterTasksByStatus(TaskStatus.DONE).size,
            tasksStatusUIModel = TaskStatus.DONE.toUIModel(),
        )
    }
}

@Preview(widthDp = 1200)
@Composable
private fun KanbanBoardPreview() {
    KanbanBoard(Tasks(tasksExample.toMutableList()))
}
