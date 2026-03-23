package woowacourse.kanban.board.component.screen

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Test
import woowacourse.kanban.board.component.kanbanboard.KanbanBoardScreenTopBar

@OptIn(ExperimentalTestApi::class)
class KanbanBoardScreenTest {
    @Test
    fun `태스크를 성공적으로 생성하면 보드에 추가되고 스낵바가 표시된다`() = runComposeUiTest {
        setContent {
            KanbanBoardScreen()
        }

        val testTitle = "통합 테스트 태스크"

        onNodeWithText("새 태스크 생성").performClick()

        onNode(hasSetTextAction() and hasAnyAncestor(hasTestTag("title_textField")))
            .performTextInput(testTitle)

        onNodeWithText("생성").performClick()
        onNodeWithText("생성").assertDoesNotExist()

        onNodeWithText(testTitle).assertIsDisplayed()

        onNodeWithText("새로운 태스크가 추가되었습니다.").assertIsDisplayed()

        onNodeWithText("완료율: 0% (0/1)").assertIsDisplayed()
    }

    @Test
    fun `새 태스크 생성 버튼을 누르면 Dialog가 생성된다`() = runComposeUiTest {
        setContent {
            KanbanBoardScreen()
        }

        onNodeWithText("새 태스크 생성").performClick()
        onNodeWithText("생성").assertIsDisplayed()
    }
}
