package woowacourse.kanban.board.ui.taskCardForm

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import woowacourse.kanban.board.model.TaskCard

@OptIn(ExperimentalTestApi::class)
class TaskCreateSectionTest {

    @Test
    fun `폼의 주요 섹션 데이터가 화면에 표시된다`() = runComposeUiTest {
        setContent { TaskCreateSection() }

        onNodeWithText("새 태스크 생성").assertIsDisplayed()
        onNodeWithText("제목 *").assertIsDisplayed()
        onNodeWithText("설명").assertIsDisplayed()
        onNodeWithText("태그").assertIsDisplayed()
        onNodeWithText("상태 *").assertIsDisplayed()
        onNodeWithText("담당자 *").assertIsDisplayed()

        onNodeWithText("다이노").assertIsDisplayed()
        onNodeWithText("페임스").assertIsDisplayed()

        onNodeWithText("To Do").assertIsDisplayed()
        onNodeWithText("In Progress").assertIsDisplayed()
        onNodeWithText("Done").assertIsDisplayed()

        onNodeWithText("취소").assertIsDisplayed()
        onNodeWithText("생성").assertIsDisplayed()
    }

    @Test
    fun `제목 입력 후 생성을 누르면 TaskCard가 생성되고 폼이 초기화된다`() = runComposeUiTest {
        var createdTask: TaskCard? = null

        setContent {
            TaskCreateSection(
                onTaskCreate = { createdTask = it },
            )
        }

        onNodeWithTag("titleInput").performTextInput("새 태스크")
        onNodeWithText("생성").performClick()

        runOnIdle {
            val created = assertNotNull(createdTask)
            assertEquals("새 태스크", created.title.text)
            assertEquals("다이노", created.assignee.name)
            assertEquals(0, created.tags.tags.size)
        }

        onNodeWithTag("titleInput", useUnmergedTree = true).assertTextEquals("")
    }
}
