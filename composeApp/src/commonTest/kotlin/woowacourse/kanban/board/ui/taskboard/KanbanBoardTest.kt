package woowacourse.kanban.board.ui.taskboard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Test
import woowacourse.kanban.board.kanbanboard.ui.KanbanBoard
import woowacourse.kanban.board.constant.TestTags
import woowacourse.kanban.board.createtaskcard.domain.TaskCardDataInputState
import woowacourse.kanban.board.kanbanboard.domain.TaskCardTable

@OptIn(ExperimentalTestApi::class)
class KanbanBoardTest {

    @Test
    fun `함수 호출 시 모든 UI 요소가 생성된다`() = runComposeUiTest {
        setContent {
            KanbanBoard(
                taskCardDataInputState = TaskCardDataInputState(),
                taskCardTable = TaskCardTable(),
            )
        }

        onNodeWithText("Compose Desktop 칸반 보드").assertExists()
        onNodeWithTag("완료율").assertExists()
        onNodeWithText("+새 태스크 생성").assertExists()
        onNodeWithText("To Do").assertExists()
        onNodeWithText("In Progress").assertExists()
        onNodeWithText("Done").assertExists()
    }

    @Test
    fun `새 태스크 생성 버튼을 누르면 다이얼로그가 표시된다`() = runComposeUiTest {
        setContent {
            KanbanBoard(
                taskCardDataInputState = TaskCardDataInputState(),
                taskCardTable = TaskCardTable(),
            )
        }

        onNodeWithText("+새 태스크 생성").performClick()

        onNodeWithText("새 태스크 생성").assertIsDisplayed()
    }

    @Test
    fun `취소 버튼을 누르면 다이얼로그가 닫히고 취소 스낵바가 표시된다`() = runComposeUiTest {
        setContent {
            KanbanBoard(
                taskCardDataInputState = TaskCardDataInputState(),
                taskCardTable = TaskCardTable(),
            )
        }

        onNodeWithText("+새 태스크 생성").performClick()
        onNodeWithText("취소").performClick()

        onNodeWithText("태스크 추가가 취소되었습니다.").assertIsDisplayed()
    }

    @Test
    fun `새 태스크를 생성하면 생성 스낵바가 표시된다`() = runComposeUiTest {
        setContent {
            KanbanBoard(
                taskCardDataInputState = TaskCardDataInputState(),
                taskCardTable = TaskCardTable(),
            )
        }

        onNodeWithText("+새 태스크 생성", useUnmergedTree = true).performClick()
        onNodeWithTag(TestTags.TITLE_INPUT, useUnmergedTree = true).performTextInput("제목")
        onNodeWithTag(TestTags.DESCRIPTION_INPUT, useUnmergedTree = true).performTextInput("설명")
        onNodeWithTag(TestTags.TAGS_INPUT, useUnmergedTree = true).performTextInput("태그1, 태그2")
        onNodeWithText("생성", useUnmergedTree = true).assertIsEnabled().performClick()

        waitForIdle()

        onNodeWithText("새로운 태스크가 추가되었습니다.", useUnmergedTree = true).assertExists()
    }

    @Test
    fun `태스크 생성 후 완료율 텍스트가 변경된다`() = runComposeUiTest {
        setContent {
            KanbanBoard(
                taskCardDataInputState = TaskCardDataInputState(),
                taskCardTable = TaskCardTable(),
            )
        }

        onNodeWithText("+새 태스크 생성").performClick()
        onNodeWithTag(TestTags.TITLE_INPUT).performTextInput("제목")
        onNodeWithTag(TestTags.DESCRIPTION_INPUT).performTextInput("설명")
        onNodeWithTag(TestTags.TAGS_INPUT).performTextInput("태그1, 태그2")
        onNodeWithTag("DoneBtn").performClick()
        onNodeWithText("생성", useUnmergedTree = true).assertIsEnabled().performClick()

        waitForIdle()

        onNodeWithTag("완료율", useUnmergedTree = true).assertTextContains("완료율 : 100% (1/1)")
    }
}