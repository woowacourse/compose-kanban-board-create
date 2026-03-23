package woowacourse.kanban.board.data

import woowacourse.kanban.board.data.Tags.Companion.TAG_DELIMITER
import kotlin.math.round

class Tasks(private val tasks: MutableList<Task>) {
    fun addNewTask(
        title: String,
        description: String,
        tags: String,
        selectedStatus: TaskStatus,
        selectedProfile: Nickname,
    ) {
        tasks.add(
            Task(
                taskTitle = Title(title),
                taskScript = Script(description),
                tags = Tags(splitTags(tags)),
                status = selectedStatus,
                nickname = selectedProfile,
            ),
        )
    }

    fun calculateDoneTasksRatio(): Double {
        val doneTasks = tasks.count { it.status == TaskStatus.DONE }
        val totalTasks = tasks.size

        if (totalTasks == 0) return 0.0
        return round((doneTasks.toDouble() / totalTasks.toDouble()) * 100)
    }

    fun tasksSize() = tasks.size
    fun doneTasksSize() = tasks.count { it.status == TaskStatus.DONE }

    fun filterTasksByStatus(status: TaskStatus): List<Task> = when(status) {
        TaskStatus.TO_DO -> tasks.filter { it.status == TaskStatus.TO_DO }
        TaskStatus.IN_PROGRESS -> tasks.filter { it.status == TaskStatus.IN_PROGRESS }
        TaskStatus.DONE -> tasks.filter { it.status == TaskStatus.DONE }
    }
    private fun splitTags(tags: String): List<String> = if (tags.isBlank()) {
        emptyList()
    } else {
        tags.split(TAG_DELIMITER).map { it.trim() }.filter { it.isNotBlank() }
    }
}
