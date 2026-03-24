package woowacourse.kanban.ui

import androidx.compose.runtime.Composable
import woowacourse.kanban.newTaskCreate.component.TaskCard
import woowacourse.kanban.newTaskCreate.data.Task

class TaskUIMapper {
    @Composable
    fun createTaskUI(
        tasks: List<Task>,
        assigneeById: Map<String, String> = emptyMap(),
    ) {
        tasks.forEach { task ->
            TaskCard(
                title = task.taskTitle,
                script = task.taskScript,
                tags = task.tags,
                nickname = assigneeById[task.assigneeId] ?: task.assigneeId,
            )
        }
    }
}
