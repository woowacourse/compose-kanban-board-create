package woowacourse.kanban.board.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class CardCreationPanelTest {

    @Test
    fun `제목을 입력하지 않으면, 생성 버튼이 비활성화된다`() = runComposeUiTest {
        // given
        val wrongTitle = "   "

        // when
        setContent {
            val uiState = rememberCardCreationState()
            uiState.taskTitle = wrongTitle
            CardCreationPanelContent(
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
            val uiState = rememberCardCreationState()
            uiState.taskTitle = wrongTitle
            CardCreationPanelContent(
                uiState = uiState,
                onCloseClick = {},
                onCreateClick = {},
            )
        }

        // then
        onNodeWithText("제목을 입력해 주세요.").assertExists()
    }

    @Test
    fun `태그를 잘못 입력하면, 생성 버튼이 비활성화된다`() = runComposeUiTest {
        // given
        val wrongTag = ",태그"

        // when
        setContent {
            val uiState = rememberCardCreationState()
            uiState.tempTags = wrongTag
            CardCreationPanelContent(
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
            val uiState = rememberCardCreationState()
            uiState.tempTags = validTag
            CardCreationPanelContent(
                uiState = uiState,
                onCloseClick = {},
                onCreateClick = {}
            )
        }

        //then
        onNodeWithText("생성").assertIsNotEnabled()
    }

    @Test
    fun `태그가 5자를 초과하면 해당 에러메시지가 출력된다`() = runComposeUiTest {
        // given
        val tooLongTag = "태그1234"

        // when
        setContent {
            val uiState = rememberCardCreationState()
            uiState.tempTags = tooLongTag
            CardCreationPanelContent(
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
            val uiState = rememberCardCreationState()
            uiState.tempTags = tooManyTag
            CardCreationPanelContent(
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
            val uiState = rememberCardCreationState()
            uiState.tempTags = invalidTag
            CardCreationPanelContent(
                uiState = uiState,
                onCloseClick = {},
                onCreateClick = {}
            )
        }

        //then
        onNodeWithText("태그 형식이 올바르지 않습니다.").assertExists()
    }
}
