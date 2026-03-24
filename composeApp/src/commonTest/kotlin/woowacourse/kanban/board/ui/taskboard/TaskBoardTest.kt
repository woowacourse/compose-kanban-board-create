package woowacourse.kanban.board.ui.taskboard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Test
import woowacourse.kanban.board.kanbanboard.ui.TaskBoard
import woowacourse.kanban.board.taskcard.domain.Manager
import woowacourse.kanban.board.taskcard.domain.State
import woowacourse.kanban.board.taskcard.domain.TaskCardData

@OptIn(ExperimentalTestApi::class)
class TaskBoardTest {
    @Test
    fun `입력된 태스크의 카드가 보드에 표시된다`() = runComposeUiTest{
        val taskCardData = listOf(TaskCardData("title", state = State.TODO, manager = Manager.DINO))

        setContent {
            TaskBoard(State.TODO, taskCardData)
        }

        onNodeWithTag("태스크 카드").assertExists()
    }

    @Test
    fun `태스크의 개수가 보드 상단에 표시된다`() = runComposeUiTest{
        val taskCardData = listOf(
            TaskCardData("title", state = State.TODO, manager = Manager.DINO),
            TaskCardData("title", state = State.TODO, manager = Manager.DINO),
        )

        setContent {
            TaskBoard(State.TODO, taskCardData)
        }

        onNodeWithText("2").assertExists()
    }

    @Test
    fun `입력된 모든 태스크는 스크롤을 통해 표시 되어야 한다`() = runComposeUiTest{
        setContent {
            TaskBoard(State.TODO, mock)
        }

        onNodeWithTag("태스크 카드 칼럼").performScrollToIndex(4)
    }

    companion object {
        private val mock = listOf(
            TaskCardData(title = "title", state = State.TODO, manager = Manager.DINO),
            TaskCardData(title = "title", state = State.TODO, manager = Manager.DINO),
            TaskCardData(title = "title", state = State.TODO, manager = Manager.DINO),
            TaskCardData(title = "title", state = State.TODO, manager = Manager.DINO),
            TaskCardData(title = "title", state = State.TODO, manager = Manager.DINO),
            TaskCardData(title = "title", state = State.TODO, manager = Manager.DINO),
        )
    }
}