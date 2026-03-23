package woowacourse.kanban.board.ui.taskCard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import woowacourse.kanban.board.fixture.TaskCardFixture
import kotlin.test.Test
import woowacourse.kanban.board.model.Tag

@OptIn(ExperimentalTestApi::class)
class TagGroupSectionTest {
    @Test
    fun `태그 그룹 뷰는 비어있는 태그 그룹을 처리할 수 있다`() = runComposeUiTest {
        val tags = Tag(emptyList())

        setContent {
            TagGroupSection(tags = tags)
        }
    }

    @Test
    fun `태그 그룹 뷰는 하나 이상의 태그를 표시한다`() = runComposeUiTest {
        val tags = Tag(listOf("태그"))

        setContent {
            TagGroupSection(tags = tags)
        }

        onNodeWithText("태그").assertIsDisplayed()
    }

    @Test
    fun `태그 그룹 뷰는 5개 이하의 태그를 표시한다`() = runComposeUiTest {
        val tags = TaskCardFixture.DEFAULT_TAG

        setContent {
            TagGroupSection(tags = tags)
        }

        onNodeWithText("태그1").assertIsDisplayed()
        onNodeWithText("태그2").assertIsDisplayed()
        onNodeWithText("태그3").assertIsDisplayed()
        onNodeWithText("태그4").assertIsDisplayed()
        onNodeWithText("태그5").assertIsDisplayed()
    }
}
