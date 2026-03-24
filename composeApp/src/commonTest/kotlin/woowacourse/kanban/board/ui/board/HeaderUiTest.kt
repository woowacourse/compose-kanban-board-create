package woowacourse.kanban.board.ui.board

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test
import kotlin.test.assertTrue
import woowacourse.kanban.board.component.Header

@OptIn(ExperimentalTestApi::class)
class HeaderUiTest {
    @Test
    fun `total과 done이 주어지면 완료율 문구를 계산해 표시한다`() = runComposeUiTest {
        setContent {
            Header(
                totalCount = 6,
                doneCount = 3,
                onClick = {},
            )
        }

        onNodeWithText("Compose Desk 칸반 보드").assertExists()
        onNodeWithText("완료율: 50% (3/6)").assertExists()
    }

    @Test
    fun `전체 태스크가 0개이면 완료율은 0퍼센트로 표시한다`() = runComposeUiTest {
        setContent {
            Header(
                totalCount = 0,
                doneCount = 0,
                onClick = {},
            )
        }

        onNodeWithText("완료율: 0% (0/0)").assertExists()
    }

    @Test
    fun `새 태스크 생성 버튼을 클릭하면 콜백이 호출된다`() = runComposeUiTest {
        var clicked = false

        setContent {
            Header(
                totalCount = 1,
                doneCount = 0,
                onClick = { clicked = true },
            )
        }

        onNodeWithText("+ 새 태스크 생성").performClick()

        waitForIdle()
        assertTrue(clicked)
    }
}
