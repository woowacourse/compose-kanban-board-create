package woowacourse.kanban.board.model

import woowacourse.kanban.board.fixture.TaskCardFixture
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TaskCardTest {
    @Test
    fun `제목이 비어있으면 에러가 발생한다`() {
        assertFailsWith<IllegalArgumentException> {
            TaskCard(
                title = " ",
                description = "설명입니다",
                status = Status.TODO,
                tags = Tag(emptyList()),
                assignee = Assignee("다이노"),
            )
        }
    }

    @Test
    fun `정상 제목으로 TaskCard를 생성할 수 있다`() {
        val taskCard = TaskCardFixture.create()

        assertEquals("제목입니다", taskCard.title)
    }
}
