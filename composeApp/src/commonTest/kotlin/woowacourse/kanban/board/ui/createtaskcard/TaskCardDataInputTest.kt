package woowacourse.kanban.board.ui.createtaskcard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Test
import woowacourse.kanban.board.constant.TestTags
import woowacourse.kanban.board.taskcard.domain.Manager
import woowacourse.kanban.board.taskcard.domain.State
import woowacourse.kanban.board.createtaskcard.domain.TaskCardDataInputState
import woowacourse.kanban.board.createtaskcard.ui.TaskCardDataInput
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class TaskCardDataInputTest {

    @Test
    fun `TaskCardDataInput 실행 시 모든 컴포저블이 생성되어야 한다`() = runComposeUiTest {

        setContent {
            TaskCardDataInput(
                state = TaskCardDataInputState(),
                onCreate = {},
                onCancel = {}
            )
        }

        onNodeWithTag(TestTags.HEADER).assertExists()
        onNodeWithTag(TestTags.TITLE_INPUT).assertExists()
        onNodeWithTag(TestTags.DESCRIPTION_INPUT).assertExists()
        onNodeWithTag(TestTags.TAGS_INPUT).assertExists()

        onNodeWithTag(TestTags.STATE_BTN).assertExists()
        onNodeWithTag("To DoBtn").assertExists()
        onNodeWithTag("In ProgressBtn").assertExists()
        onNodeWithTag("DoneBtn").assertExists()

        onNodeWithTag(TestTags.MANAGER_BTN).assertExists()
        onNodeWithTag("다이노Btn").assertExists()
        onNodeWithTag("페임스Btn").assertExists()

        onNodeWithTag(TestTags.FOOTER).assertExists()
        onNodeWithText("취소").assertExists()
        onNodeWithText("생성").assertExists()
    }

    @Test
    fun `제목이 입력되면 생성 버튼이 활성화 된다`() = runComposeUiTest {
        setContent {
            TaskCardDataInput(
                state = TaskCardDataInputState(),
                onCreate = {},
                onCancel = {}
            )
        }

        onNodeWithTag(TestTags.TITLE_INPUT).performTextInput("제목")

        onNodeWithText("생성").assertIsEnabled()
    }

    @Test
    fun `제목이 입력되고 태그가 잘못 입력되면 생성 버튼이 활성화되지 않는다`() = runComposeUiTest {
        setContent {
            TaskCardDataInput(
                state = TaskCardDataInputState(),
                onCreate = {},
                onCancel = {}
            )
        }

        onNodeWithTag(TestTags.TITLE_INPUT).performTextInput("제목")
        onNodeWithTag(TestTags.TAGS_INPUT).performTextInput("잘못된 입력")

        onNodeWithText("생성").assertIsNotEnabled()
    }

    @Test
    fun `각 상태 버튼을 누르면 해당하는 값이 저장된다`() = runComposeUiTest {
        val state = TaskCardDataInputState()

        setContent {
            TaskCardDataInput(
                state = state,
                onCreate = {},
                onCancel = {}
            )
        }


        onNodeWithTag("To DoBtn").performClick()
        assertEquals(State.TODO, state.selectedState)

        onNodeWithTag("In ProgressBtn").performClick()
        assertEquals(State.IN_PROGRESS, state.selectedState)

        onNodeWithTag("DoneBtn").performClick()
        assertEquals(State.DONE, state.selectedState)
    }

    @Test
    fun `각 담당자 버튼을 누르면 해당하는 값이 저장된다`() = runComposeUiTest {
        val state = TaskCardDataInputState()

        setContent {
            TaskCardDataInput(
                state = state,
                onCreate = {},
                onCancel = {}
            )
        }


        onNodeWithTag("다이노Btn").performClick()
        assertEquals(Manager.DINO, state.selectedManager)

        onNodeWithTag("페임스Btn").performClick()
        assertEquals(Manager.FAMES, state.selectedManager)
    }
}