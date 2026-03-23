package woowacourse.kanban.board.ui.kanbanBoard

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class KanbanBoardHeaderSectionTest {
    @Test
    fun `완료율이 0일 때 완료율 텍스트를 표시한다`() = runComposeUiTest {
        setContent {
            MaterialTheme {
                KanbanBoardHeaderSection(
                    totalCount = 0,
                    doneCount = 0,
                    onCreateClick = {},
                )
            }
        }

        onNodeWithText("완료율 : 0% (0/0)").assertIsDisplayed()
    }

    @Test
    fun `완료율이 존재할 때 완료율 텍스트를 표시한다`() = runComposeUiTest {
        setContent {
            MaterialTheme {
                KanbanBoardHeaderSection(
                    totalCount = 4,
                    doneCount = 1,
                    onCreateClick = {},
                )
            }
        }

        onNodeWithText("완료율 : 25% (1/4)").assertIsDisplayed()
    }

    @Test
    fun `새 테스크 생성 버튼을 클릭하면 콜백이 실행된다`() = runComposeUiTest {
        var clicked = false

        setContent {
            MaterialTheme {
                KanbanBoardHeaderSection(
                    totalCount = 1,
                    doneCount = 0,
                    onCreateClick = { clicked = true },
                )
            }
        }

        onNodeWithText("새 테스크 생성").performClick()

        runOnIdle {
            assertTrue(clicked)
        }
    }
}
