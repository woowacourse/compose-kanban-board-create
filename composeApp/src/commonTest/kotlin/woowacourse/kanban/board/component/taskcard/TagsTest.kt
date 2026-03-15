package woowacourse.kanban.board.component.taskcard

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import woowacourse.kanban.board.model.modal.Tags
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class TagsTest {

    @Test
    fun `Tags에 빈 태그 리스트가 들어오면 Tags 컴포넌트가 출력되지 않는다`() = runComposeUiTest {
        setContent {
            Tags(
                tags = emptyList(),
                maxTagCount = Tags.MAX_TAGS,
                maxTagTextLength = Tags.TAG_MAX_TEXT_LENGTH,
                modifier = Modifier.testTag("tags"),
            )
        }
        onNodeWithTag("tags").assertDoesNotExist()
    }

    @Test
    fun `Tags에 요소가 1개 이상인 태그 리스트가 들어오면 Tags 컴포넌트가 출력된다`() = runComposeUiTest {
        setContent {
            Tags(
                tags = listOf("태그1", "태그2"),
                maxTagCount = Tags.MAX_TAGS,
                maxTagTextLength = Tags.TAG_MAX_TEXT_LENGTH,
                modifier = Modifier.testTag("tags"),
            )
        }
        onNodeWithTag("tags").assertExists()
    }

    @Test
    fun `Tags의 태그 최대 개수를 6개로 설정하면 6개까지 Tag 컴포넌트가 출력된다`() = runComposeUiTest {
        val tags = listOf("1", "2", "3", "4", "5", "6")
        setContent {
            Tags(
                tags = tags,
                maxTagCount = 6,
                maxTagTextLength = Tags.TAG_MAX_TEXT_LENGTH,
            )
        }
        tags.forEach { tag ->
            onNodeWithText(tag).assertExists()
        }
    }
}
