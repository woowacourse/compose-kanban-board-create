package woowacourse.kanban.board.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import woowacourse.kanban.board.ui.board.BoardScreen
import woowacourse.kanban.board.ui.board.BoardState
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class BoardScreenTest {
    @Test
    fun `생성 버튼을 누르면, 스낵바 알림이 뜬다`() = runComposeUiTest {
        // given
        val state = BoardState()
        setContent {
            BoardScreen(boardState = state)
        }
        onNodeWithText("새 태스크 생성").performClick()
        onNodeWithText("태스크 제목을 입력하세요").performTextInput("제목")

        // when
        onNodeWithText("생성").performClick()

        // then
        onNodeWithText("새로운 태스크가 추가되었습니다.").assertExists()
    }
}
