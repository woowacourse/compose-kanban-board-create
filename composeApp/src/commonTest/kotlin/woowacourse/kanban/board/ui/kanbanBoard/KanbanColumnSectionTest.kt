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
class KanbanColumnSectionTest {
    @Test
    fun `컬럼은 상태 라벨과 태스크 개수를 표시한다`() = runComposeUiTest {
        val assignee = Assignee("다이노")
        val tasks = listOf(
            TaskCard("제목1", "설명", Status.TODO, Tag(emptyList()), assignee),
            TaskCard("제목2", "설명", Status.TODO, Tag(emptyList()), assignee),
        )

        setContent {
            MaterialTheme {
                KanbanColumnSection(status = Status.TODO, tasks = tasks)
            }
        }

        onNodeWithText(UiText.STATUS_TODO).assertIsDisplayed()
        onNodeWithText("2").assertIsDisplayed()
    }

    @Test
    fun `컬럼은 태스크 카드를 표시한다`() = runComposeUiTest {
        val assignee = Assignee("다이노")
        val tasks = listOf(
            TaskCard("제목1", "설명", Status.TODO, Tag(emptyList()), assignee),
            TaskCard("제목2", "설명", Status.TODO, Tag(emptyList()), assignee),
        )

        setContent {
            MaterialTheme {
                KanbanColumnSection(status = Status.TODO, tasks = tasks)
            }
        }

        onNodeWithText("제목1").assertIsDisplayed()
        onNodeWithText("제목2").assertIsDisplayed()
    }
}
