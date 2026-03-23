package woowacourse.kanban.board.ui.board

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertRangeInfoEquals
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class BoardHeaderTest {

    @Test
    fun `생성하기 버튼을 눌렀을 때 KanbanDialog가 뜬다`() = runComposeUiTest {

        setContent {
            KanbanBoard()
        }

        // 칸반 크리에이트 헤더에 있는 icon 라벨로 구분
        onNodeWithContentDescription("테스크 종료", useUnmergedTree = true)
            .assertDoesNotExist()

        onNodeWithText("새 태스크 생성").performClick()

        onAllNodesWithText("새 태스크 생성", useUnmergedTree = true).assertCountEquals(2)
        onNodeWithContentDescription("테스크 종료", useUnmergedTree = true).assertExists()
    }

    @Test
    fun `다이얼로그 닫기 클릭 시 다이얼로그가 닫힌다`() = runComposeUiTest {
        setContent {
            KanbanBoard()
        }

        onNodeWithText("새 태스크 생성").performClick()
        onNodeWithContentDescription("테스크 종료", useUnmergedTree = true).performClick()

        onNodeWithContentDescription("테스크 종료", useUnmergedTree = true)
            .assertDoesNotExist()
    }


    @Test
    fun `완료율은 Done 나누기 전체 테스크 수 곱하기 100 으로 계산되어 표시된다`() {
        val result = taskComplete(5, 10)

        assertEquals(50, result)
    }

    @Test
    fun `전체 테스크 수가 0이면 완료율은 0으로 표시한다`() {
        val result = taskComplete(0, 0)

        assertEquals(0, result)
    }

    @Test
    fun `Done 테스크 수가 0이면 완료율은 0으로 표시한다`() {
        val result = taskComplete(0, 8)

        assertEquals(0, result)
    }

    @Test
    fun `완료율에 맞춰 바의 길이가 변한다`() = runComposeUiTest {
        setContent {
            BoardInfoHeader_Progress(taskComplete = 50)
        }

        onNodeWithTag(BOARD_PROGRESS_TEST_TAG)
            .assertRangeInfoEquals(ProgressBarRangeInfo(0.5f, 0f..1f))
    }
}
