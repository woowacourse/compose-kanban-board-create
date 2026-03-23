package woowacourse.kanban.board.component.kanbanboard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Test
import woowacourse.kanban.board.data.Nickname
import woowacourse.kanban.board.data.Script
import woowacourse.kanban.board.data.Task
import woowacourse.kanban.board.data.TaskStatus
import woowacourse.kanban.board.data.Tasks
import woowacourse.kanban.board.data.Title

@OptIn(ExperimentalTestApi::class)
class KanbanBoardTest {
    @Test
    fun `칸반 보드에 세 가지 상태 컬럼이 모두 표시된다`() = runComposeUiTest {
        setContent {
            KanbanBoard(Tasks(mutableListOf()))
        }

        onNodeWithText("To Do").assertIsDisplayed()
        onNodeWithText("In Progress").assertIsDisplayed()
        onNodeWithText("Done").assertIsDisplayed()
    }

    @Test
    fun `태스크가 하나도 없을 때 모든 컬럼의 개수는 0으로 표시된다`() = runComposeUiTest {
        setContent {
            KanbanBoard(Tasks(mutableListOf()))
        }

        onAllNodesWithText("0").assertCountEquals(3)
    }
}
