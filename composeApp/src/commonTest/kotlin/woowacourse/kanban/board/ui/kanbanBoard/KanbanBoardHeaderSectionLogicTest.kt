package woowacourse.kanban.board.ui.kanbanBoard

import kotlin.test.Test
import kotlin.test.assertEquals

class KanbanBoardHeaderSectionLogicTest {
    @Test
    fun `완료율 텍스트는 전체가 0일 때 0퍼센트를 반환한다`() {
        val text = completionRateText(totalCount = 0, doneCount = 0)
        assertEquals("완료율 : 0% (0/0)", text)
    }

    @Test
    fun `완료율 텍스트는 완료율을 퍼센트로 계산한다`() {
        val text = completionRateText(totalCount = 4, doneCount = 1)
        assertEquals("완료율 : 25% (1/4)", text)
    }

    @Test
    fun `완료율 진행값을 계산한다`() {
        val progress = completionProgress(totalCount = 4, doneCount = 1)
        assertEquals(0.25f, progress, absoluteTolerance = 0.0001f)
    }
}
