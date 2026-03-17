package woowacourse.kanban.board.component.createtaskcard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Test
import woowacourse.kanban.board.constant.TestTags

@OptIn(ExperimentalTestApi::class)
class TaskCardDataInputTest {

    @Test
    fun `TaskCardDataInput 실행 시 모든 컴포저블이 생성되어야 한다`() = runComposeUiTest {
        setContent {
            TaskCardDataInput()
        }

        onNodeWithTag(TestTags.HEADER).assertExists()
        onNodeWithTag(TestTags.TITLE_INPUT).assertExists()
        onNodeWithTag(TestTags.DESCRIPTION_INPUT).assertExists()
        onNodeWithTag(TestTags.TAGS_INPUT).assertExists()
        onNodeWithTag(TestTags.STATE_BTN).assertExists()
        onNodeWithTag(TestTags.MANAGER_BTN).assertExists()
        onNodeWithTag(TestTags.FOOTER).assertExists()
    }
}