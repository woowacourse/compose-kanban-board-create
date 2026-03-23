package woowacourse.kanban.board.ui.taskCard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import woowacourse.kanban.board.fixture.TaskCardFixture
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class TitleSectionTest {
    @Test
    fun `제목 뷰는 제목 텍스트를 표시한다`() = runComposeUiTest {
        val title = TaskCardFixture.DEFAULT_TITLE

        setContent {
            TitleSection(title = title)
        }

        onNodeWithText(TaskCardFixture.DEFAULT_TITLE).assertIsDisplayed()
    }
}
