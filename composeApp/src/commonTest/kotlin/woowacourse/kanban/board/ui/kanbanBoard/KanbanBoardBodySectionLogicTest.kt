package woowacourse.kanban.board.ui.kanbanBoard

import kotlin.test.Test
import kotlin.test.assertEquals
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.Status
import woowacourse.kanban.board.model.Tag
import woowacourse.kanban.board.model.TaskCard

class KanbanBoardBodySectionLogicTest {
    @Test
    fun `상태별로 태스크를 분리한다`() {
        val assignee = Assignee("다이노")
        val todoTask = TaskCard("TODO", "설명", Status.TODO, Tag(emptyList()), assignee)
        val doneTask = TaskCard("DONE", "설명", Status.DONE, Tag(emptyList()), assignee)
        val tasks = listOf(todoTask, doneTask)

        val todos = tasksByStatus(tasks, Status.TODO)
        val dones = tasksByStatus(tasks, Status.DONE)

        assertEquals(listOf(todoTask), todos)
        assertEquals(listOf(doneTask), dones)
    }
}
