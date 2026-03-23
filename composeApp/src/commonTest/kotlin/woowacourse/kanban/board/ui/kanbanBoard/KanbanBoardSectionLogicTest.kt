package woowacourse.kanban.board.ui.kanbanBoard

import kotlin.test.Test
import kotlin.test.assertEquals
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.Status
import woowacourse.kanban.board.model.Tag
import woowacourse.kanban.board.model.TaskCard

class KanbanBoardSectionLogicTest {
    @Test
    fun `전체 태스크 수와 완료 태스크 수를 계산한다`() {
        val assignee = Assignee("다이노")
        val tasks = listOf(
            TaskCard("제목1", "설명", Status.TODO, Tag(emptyList()), assignee),
            TaskCard("제목2", "설명", Status.DONE, Tag(emptyList()), assignee),
            TaskCard("제목3", "설명", Status.DONE, Tag(emptyList()), assignee),
        )

        val counts = calculateBoardCounts(tasks)

        assertEquals(3, counts.totalCount)
        assertEquals(2, counts.doneCount)
    }
}
