package woowacourse.kanban.board.ui.taskBoard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.Description
import woowacourse.kanban.board.model.TagGroup
import woowacourse.kanban.board.model.TaskCard
import woowacourse.kanban.board.model.TaskStatus
import woowacourse.kanban.board.model.Title

@OptIn(ExperimentalTestApi::class)
class TaskBoardSectionTest {

    @Test
    fun `칸반 보드는 상태 컬럼과 개수를 표시한다`() = runComposeUiTest {
        setContent {
            TaskBoardSection(tasks = listOf(todoTask(), doneTask()))
        }

        onNodeWithText("To Do").assertIsDisplayed()
        onNodeWithText("In Progress").assertIsDisplayed()
        onNodeWithText("Done").assertIsDisplayed()

        onAllNodesWithText("1").assertCountEquals(2)
        onNodeWithText("완료율: 50% (1/2)").assertIsDisplayed()
    }

    @Test
    fun `새 태스크 생성 버튼을 누르면 생성 모달이 나타난다`() = runComposeUiTest {
        setContent {
            TaskBoardSection(tasks = emptyList())
        }

        onNodeWithText("+ 새 태스크 생성").performClick()

        onNodeWithText("새 태스크 생성").assertIsDisplayed()
        onNodeWithText("제목 *").assertIsDisplayed()
    }

    @Test
    fun `태스크 생성 성공 시 카드와 스낵바 메시지가 표시된다`() = runComposeUiTest {
        setContent {
            TaskBoardSection(tasks = emptyList())
        }

        onNodeWithText("+ 새 태스크 생성").performClick()
        onNodeWithTag("titleInput").performTextInput("새 태스크")
        onNodeWithText("생성").performClick()

        onNodeWithText("새 태스크").assertIsDisplayed()
        onNodeWithText("새로운 태스크가 추가되었습니다.").assertIsDisplayed()
    }

    private fun todoTask() = TaskCard(
        title = Title("할 일"),
        status = TaskStatus.TODO,
        assignee = Assignee("다이노"),
        description = Description("todo 설명"),
        tags = TagGroup(emptyList()),
    )

    private fun doneTask() = TaskCard(
        title = Title("완료된 일"),
        status = TaskStatus.DONE,
        assignee = Assignee("페임스"),
        description = Description("done 설명"),
        tags = TagGroup(emptyList()),
    )
}

