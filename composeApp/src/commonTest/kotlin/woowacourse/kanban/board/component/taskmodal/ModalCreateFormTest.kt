package woowacourse.kanban.board.component.taskmodal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.Density
import woowacourse.kanban.board.model.Assignee
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class ModalCreateFormTest {
    private val testAssignees = listOf(Assignee("커비"), Assignee("바드"), Assignee("하로"))

    private val modalSetUp: @Composable (ModalCreateFormState) -> Unit = { state ->
        CompositionLocalProvider(LocalDensity provides Density(density = 0.1f)) {
            ModalCreateForm(
                assignees = testAssignees,
                onClickCancel = {},
                onClickConfirm = {},
                modalState = state,
            )
        }
    }

    @Test
    fun `제목을 입력하지 않으면 에러 메시지가 표시된다`() = runComposeUiTest {
        // given
        setContent {
            val state = remember { ModalCreateFormState() }
            modalSetUp(state)
        }
        // then
        onNodeWithText("제목을 입력해주세요").assertExists()
    }

    @Test
    fun `제목을 입력하면 에러 메시지가 표시되지 않는다`() = runComposeUiTest {
        // given
        setContent {
            val state = remember { ModalCreateFormState() }
            modalSetUp(state)
        }

        // when
        onNodeWithText("태스크 제목을 입력하세요").performTextInput("새로운 제목")
        onNodeWithText("생성").performClick()

        // then
        onNodeWithText("제목을 입력해주세요").assertDoesNotExist()
    }

    @Test
    fun `올바르지 않은 태그 형식을 입력하면 에러 메시지가 표시된다`() = runComposeUiTest {
        // given
        setContent {
            val state = remember { ModalCreateFormState() }
            modalSetUp(state)
        }

        // when
        onNodeWithText("태그를 쉼표로 구분하여 입력하세요 (예: 버그, 긴급)").performTextInput("태그1,,태그2")
        onNodeWithText("생성").performClick()

        // then
        onNodeWithText("태그 형식이 올바르지 않습니다.").assertExists()
    }

    @Test
    fun `5자를 초과하는 태그를 입력하면 에러 메시지가 표시된다`() = runComposeUiTest {
        // given
        setContent {
            val state = remember { ModalCreateFormState() }
            modalSetUp(state)
        }

        // when
        onNodeWithText("태그를 쉼표로 구분하여 입력하세요 (예: 버그, 긴급)").performTextInput("여섯글자태그")
        onNodeWithText("생성").performClick()

        // then
        onNodeWithText("태그는 5자 이내로 5개까지만 등록할 수 있습니다.").assertExists()
    }
}
