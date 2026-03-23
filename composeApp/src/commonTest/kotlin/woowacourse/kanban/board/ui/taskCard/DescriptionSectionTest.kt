package woowacourse.kanban.board.ui.taskCard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import woowacourse.kanban.board.fixture.TaskCardFixture
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class DescriptionSectionTest {
    @Test
    fun `설명 뷰는 설명 텍스트를 표시한다`() = runComposeUiTest {
        val description = TaskCardFixture.DEFAULT_DESCRIPTION

        setContent {
            DescriptionSection(description = description)
        }

        onNodeWithText(TaskCardFixture.DEFAULT_DESCRIPTION).assertIsDisplayed()
    }

    @Test
    fun `설명 뷰는 최대 두 줄까지 표시한다`() = runComposeUiTest {
        val description = "1\n2"

        setContent {
            DescriptionSection(description = description)
        }

        val descriptionNode = onNodeWithText("2", substring = true)
        descriptionNode.assertIsDisplayed()
    }
}
