package woowacourse.kanban.board.ui.kanbanBoard

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.Status
import woowacourse.kanban.board.model.Tag
import woowacourse.kanban.board.model.TaskCard
import woowacourse.kanban.board.util.Text as UiText

@OptIn(ExperimentalTestApi::class)
class KanbanBoardBodySectionTest {
    @Test
    fun `보드 바디는 상태별 컬럼과 태스크를 표시한다`() = runComposeUiTest {
        val assignee = Assignee("다이노")
        val tasks = listOf(
            TaskCard("TODO 카드", "설명", Status.TODO, Tag(emptyList()), assignee),
            TaskCard("INPROGRESS 카드", "설명", Status.INPROGRESS, Tag(emptyList()), assignee),
            TaskCard("DONE 카드", "설명", Status.DONE, Tag(emptyList()), assignee),
        )

        setContent {
            MaterialTheme {
                KanbanBoardBodySection(taskCards = tasks)
            }
        }

        onNodeWithText(UiText.STATUS_TODO).assertIsDisplayed()
        onNodeWithText(UiText.STATUS_IN_PROGRESS).assertIsDisplayed()
        onNodeWithText(UiText.STATUS_DONE).assertIsDisplayed()

        onNodeWithText("TODO 카드").assertIsDisplayed()
        onNodeWithText("INPROGRESS 카드").assertIsDisplayed()
        onNodeWithText("DONE 카드").assertIsDisplayed()
    }
}
