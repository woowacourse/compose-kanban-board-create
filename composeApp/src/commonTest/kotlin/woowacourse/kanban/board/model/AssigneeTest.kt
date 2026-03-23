package woowacourse.kanban.board.model

import kotlin.test.Test
import kotlin.test.assertFailsWith

class AssigneeTest {
    private val blankText = " "
    private val validText = "다이노"

    @Test
    fun `담당자는 빈 텍스트일 수 없다`() {
        assertFailsWith<IllegalArgumentException> {
            createAssignee(blankText)
        }
    }

    @Test
    fun `담당자는 텍스트 입력 시, 객체가 생성된다`() {
        val assignee = createAssignee(validText)
        assert(assignee.name == validText)
    }

    private fun createAssignee(name: String): Assignee {
        return Assignee(name)
    }
}
