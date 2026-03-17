package woowacourse.kanban.board.component.taskcard

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import woowacourse.kanban.board.model.modal.Tags
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class TagsTest {

    @Test
    fun `Tags에 빈 태그 리스트가 들어오면 Tags 컴포넌트가 출력되지 않는다`() = runComposeUiTest {
        setContent {
            Tags(tags = Tags(""),
                modifier = Modifier.testTag("tags"),
            )
        }
        onNodeWithTag("tags").assertDoesNotExist()
    }

    @Test
    fun `Tags에 요소가 1개 이상인 태그 리스트가 들어오면 Tags 컴포넌트가 출력된다`() = runComposeUiTest {
        setContent {
            Tags(tags = Tags(value = "태그1,태그2"),
                modifier = Modifier.testTag("tags"),
            )
        }
        onNodeWithTag("tags").assertExists()
    }
}
