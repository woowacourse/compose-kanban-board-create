package woowacourse.kanban.board.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Test
import woowacourse.kanban.board.ui.taskcardform.TaskCardFormContent
import woowacourse.kanban.board.ui.taskcardform.rememberCardFormState

@OptIn(ExperimentalTestApi::class)
class TaskCardFormScreenTest {

    @Test
    fun `제목을 입력하지 않으면, 생성 버튼이 비활성화된다`() = runComposeUiTest {
        // given
        val wrongTitle = "   "

        // when
        setContent {
            val uiState = rememberCardFormState()
            uiState.taskTitle = wrongTitle
            TaskCardFormContent(
                uiState = uiState,
                onCloseClick = {},
                onCreateClick = {},
            )
        }

        // then
        onNodeWithText("생성").assertIsNotEnabled()
    }

    @Test
    fun `제목을 입력하지 않으면 에러메시지가 노출된다`() = runComposeUiTest {
        // given
        val wrongTitle = "   "

        // when
        setContent {
            val uiState = rememberCardFormState()
            uiState.taskTitle = wrongTitle
            TaskCardFormContent(
                uiState = uiState,
                onCloseClick = {},
                onCreateClick = {},
            )
        }

        // then
        onNodeWithText("제목을 입력해 주세요.").assertExists()
    }

    @Test
    fun `제목을 입력하면 에러메시지가 노출되지 않는다`() = runComposeUiTest {
        // given
        val validTitle = "제목"

        // when
        setContent {
            val uiState = rememberCardFormState()
            uiState.taskTitle = validTitle
            TaskCardFormContent(
                uiState = uiState,
                onCloseClick = {},
                onCreateClick = {},
            )
        }

        // then
        onNodeWithText("제목을 입력해 주세요.").assertDoesNotExist()
    }

    @Test
    fun `태그를 잘못 입력하면, 생성 버튼이 비활성화된다`() = runComposeUiTest {
        // given
        val wrongTag = ",태그"

        // when
        setContent {
            val uiState = rememberCardFormState()
            uiState.tempTags = wrongTag
            TaskCardFormContent(
                uiState = uiState,
                onCloseClick = {},
                onCreateClick = {}
            )
        }

        //then
        onNodeWithText("생성").assertIsNotEnabled()
    }

    @Test
    fun `유효한 태그를 입력하면, 생성 버튼이 활성화된다`() = runComposeUiTest {
        // given
        val validTag = "태그1,태그2"

        // when
        setContent {
            val uiState = rememberCardFormState()
            uiState.taskTitle = "기본 제목"
            uiState.tempTags = validTag
            TaskCardFormContent(
                uiState = uiState,
                onCloseClick = {},
                onCreateClick = {}
            )
        }

        //then
        onNodeWithText("생성").assertIsEnabled()
    }

    @Test
    fun `태그가 5자를 초과하면 해당 에러메시지가 출력된다`() = runComposeUiTest {
        // given
        val tooLongTag = "태그1234"

        // when
        setContent {
            val uiState = rememberCardFormState()
            uiState.tempTags = tooLongTag
            TaskCardFormContent(
                uiState = uiState,
                onCloseClick = {},
                onCreateClick = {}
            )
        }

        //then
        onNodeWithText("태그는 5자 이내로 5개까지만 등록할 수 있습니다.").assertExists()
    }

    @Test
    fun `태그 개수가 5개를 초과하면 해당 에러메시지가 출력된다`() = runComposeUiTest {
        // given
        val tooManyTag = "1,2,3,4,5,6"

        // when
        setContent {
            val uiState = rememberCardFormState()
            uiState.tempTags = tooManyTag
            TaskCardFormContent(
                uiState = uiState,
                onCloseClick = {},
                onCreateClick = {}
            )
        }

        //then
        onNodeWithText("태그는 5자 이내로 5개까지만 등록할 수 있습니다.").assertExists()
    }

    @Test
    fun `태그 형식이 유효하지 않으면 해당 에러메시지가 출력된다`() = runComposeUiTest {
        // given
        val invalidTag = "1,,"

        // when
        setContent {
            val uiState = rememberCardFormState()
            uiState.tempTags = invalidTag
            TaskCardFormContent(
                uiState = uiState,
                onCloseClick = {},
                onCreateClick = {}
            )
        }

        //then
        onNodeWithText("태그 형식이 올바르지 않습니다.").assertExists()
    }

    @Test
    fun `유효한 태그를 입력했을 때 정상 안내 메시지가 출력된다`() = runComposeUiTest {
        // given
        val validTag = "1,2,3,4,5"

        // when
        setContent {
            val uiState = rememberCardFormState()
            uiState.tempTags = validTag
            TaskCardFormContent(
                uiState = uiState,
                onCloseClick = {},
                onCreateClick = {}
            )
        }

        // then
        onNodeWithText("5자 이내의 태그를 최대 5개까지 등록할 수 있습니다.").assertExists()
    }
}
