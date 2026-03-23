package woowacourse.kanban.board.ui.kanbanBoard

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.Status
import woowacourse.kanban.board.model.Tag
import woowacourse.kanban.board.model.TaskCard

@OptIn(ExperimentalTestApi::class)
class KanbanBoardSectionTest {
    @Test
    fun `생성 버튼을 누르면 태스크 생성 다이얼로그가 나타난다`() = runComposeUiTest {
        val assignee = Assignee("다이노")
        val tasks = listOf(
            TaskCard("제목1", "설명", Status.TODO, Tag(emptyList()), assignee),
            TaskCard("제목2", "설명", Status.DONE, Tag(emptyList()), assignee),
        )

        setContent {
            MaterialTheme {
                KanbanBoardSection(taskCards = tasks)
            }
        }

        onNodeWithText("취소").assertDoesNotExist()

        onNodeWithText("새 테스크 생성").performClick()

        onNodeWithText("취소").assertIsDisplayed()
    }
}
