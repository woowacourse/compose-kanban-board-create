package woowacourse.kanban.board.ui.kanbanBoard

import kotlin.test.Test
import kotlin.test.assertEquals
import woowacourse.kanban.board.model.Status
import woowacourse.kanban.board.util.ColorPalette

class KanbanColumnSectionLogicTest {
    @Test
    fun `상태에 따라 컬럼 색상을 선택한다`() {
        val (todoHeader, todoBody, todoBorder) = selectColor(Status.TODO)
        assertEquals(ColorPalette.BoxHeader.Todo, todoHeader)
        assertEquals(ColorPalette.BoxBody.Todo, todoBody)
        assertEquals(ColorPalette.Border.Todo, todoBorder)

        val (progressHeader, progressBody, progressBorder) = selectColor(Status.INPROGRESS)
        assertEquals(ColorPalette.BoxHeader.InProgress, progressHeader)
        assertEquals(ColorPalette.BoxBody.InProgress, progressBody)
        assertEquals(ColorPalette.Border.InProgress, progressBorder)

        val (doneHeader, doneBody, doneBorder) = selectColor(Status.DONE)
        assertEquals(ColorPalette.BoxHeader.Done, doneHeader)
        assertEquals(ColorPalette.BoxBody.Done, doneBody)
        assertEquals(ColorPalette.Border.Done, doneBorder)
    }
}
