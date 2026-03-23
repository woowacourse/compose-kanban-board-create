package woowacourse.kanban.board.component.taskboard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import woowacourse.kanban.board.component.taskcard.KanbanCardForm
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.TaskState
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class KanbanBoardTest {

    @Test
    fun `KanbanCardForm이 올바르게 입력되면 KanbanCardForm 타입의 객체가 태스크 리스트에 추가된다`() = runComposeUiTest {
        // given
        setContent {
            KanbanBoard()
        }

        onNodeWithText("새 태스크 생성").performClick()
        onNodeWithText("태스크 제목을 입력하세요").performTextInput("새로운 테스트 태스크")
        onNodeWithText("생성").performClick()

        onNodeWithText("새로운 테스트 태스크").assertIsDisplayed()
    }

    @Test
    fun `어떤 타입의 태스크가 태스크 리스트에 추가되면 해당 상태의 태스크 개수가 1개 증가한다`() = runComposeUiTest {
        // given
        // when
        setContent {
            KanbanBoard()
        }

        // then
        onAllNodesWithText("0").assertCountEquals(3)

        onNodeWithText("새 태스크 생성").performClick()
        onNodeWithText("태스크 제목을 입력하세요").performTextInput("To Do 태스크")
        onNodeWithText("생성").performClick()
        
        onNodeWithText("1").assertIsDisplayed()
        onAllNodesWithText("0").assertCountEquals(2)
    }

    @Test
    fun `새 테스크 생성 버튼을 누르면 다이얼로그가 표시된다`() = runComposeUiTest {
        // given
        setContent {
            KanbanBoard()
        }

        // when
        onNodeWithText("새 태스크 생성").performClick()

        // then
        onAllNodesWithText("새 태스크 생성").assertCountEquals(2)
    }

    @Test
    fun `다이얼로그의 취소 버튼을 누르면 다이얼로그가 사라진다`() = runComposeUiTest {
        // given
        setContent {
            KanbanBoard()
        }

        // when
        onNodeWithText("새 태스크 생성").performClick()
        onNodeWithText("취소").performClick()

        // then
        onNodeWithText("취소").assertDoesNotExist()
    }

    @Test
    fun `다이얼로그의 X 버튼을 누르면 다이얼로그가 사라진다`() = runComposeUiTest {
        // given
        setContent {
            KanbanBoard()
        }

        // when
        onNodeWithText("새 태스크 생성").performClick()
        onNodeWithContentDescription("모달 닫기").performClick()

        // then
        onNodeWithContentDescription("모달 닫기").assertDoesNotExist()
    }

    @Test
    fun `다이얼로그에서 정상적인 값을 입력 후 생성 버튼을 누르면 칸반 보드 리스트에 표시된다`() = runComposeUiTest {
        // given
        setContent {
            KanbanBoard()
        }

        // when
        onNodeWithText("새 태스크 생성").performClick()
        onNodeWithText("태스크 제목을 입력하세요").performTextInput("정상 태스크")
        onNodeWithText("생성").performClick()

        // then
        onNodeWithText("정상 태스크").assertIsDisplayed()
    }

    @Test
    fun `태스크가 정상적으로 생성되면 스낵바가 나타난다`() = runComposeUiTest {
        // given
        setContent {
            KanbanBoard()
        }

        // when
        onNodeWithText("새 태스크 생성").performClick()
        onNodeWithText("태스크 제목을 입력하세요").performTextInput("스낵바 테스트")
        onNodeWithText("생성").performClick()

        // then
        onNodeWithText("스낵바 테스트 태스크가 생성되었습니다.").assertIsDisplayed()
    }

    @Test
    fun `완료된 태스크가 없으면 0%가 표시된다`() = runComposeUiTest {
        // given
        setContent {
            KanbanBoard()
        }

        // when
        onNodeWithText("완료율: 0% (0/0)").assertIsDisplayed()
        onNodeWithText("새 태스크 생성").performClick()
        onNodeWithText("태스크 제목을 입력하세요").performTextInput("미완료 태스크")
        onNodeWithText("생성").performClick()

        // then
        onNodeWithText("완료율: 0% (0/1)").assertIsDisplayed()
    }

    @Test
    fun `태스크 리스트를 전달하면 정확한 완료율과 개수를 표시한다`() = runComposeUiTest {
        // given
        val testList = listOf(
            KanbanCardForm(
                title = "TODO 테스크",
                assignee = Assignee("담당자1"),
                status = TaskState.TODO,
            ),
            KanbanCardForm(
                title = "DONE 테스크",
                assignee = Assignee("담당자2"),
                status = TaskState.DONE,
            )
        )
        // when
        setContent {
            KanbanTaskBoardHeader(
                taskList = testList,
                onClick = {},
            )
        }
        // then
        onNodeWithText("완료율: 50% (1/2)").assertExists()
    }
}
