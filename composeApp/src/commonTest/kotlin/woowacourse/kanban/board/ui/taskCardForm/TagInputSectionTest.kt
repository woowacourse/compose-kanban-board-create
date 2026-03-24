package woowacourse.kanban.board.ui.taskCardForm

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import woowacourse.kanban.board.model.TagGroup
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class TagInputSectionTest {
    @Test
    fun `태그 입력은 상태를 갱신한다`() = runComposeUiTest {
        var tags by mutableStateOf("")

        setContent {
            MaterialTheme {
                TagInputSection(
                    tags = tags,
                    onTagsChange = { tags = it },
                    errorMessage = validateTagError(tags),
                )
            }
        }

        onNode(hasSetTextAction()).performTextInput("버그, 긴급")

        runOnIdle {
            assertEquals("버그, 긴급", tags)
        }
    }

    @Test
    fun `태그 형식이 올바르지 않으면 에러 메시지와 아이콘을 표시한다`() = runComposeUiTest {
        setContent {
            var tags by mutableStateOf("")
            MaterialTheme {
                TagInputSection(
                    tags = tags,
                    onTagsChange = { tags = it },
                    errorMessage = validateTagError(tags),
                )
            }
        }

        onNode(hasSetTextAction()).performTextInput("태그,")

        onNodeWithText("태그 형식이 올바르지 않습니다.", useUnmergedTree = true).assertIsDisplayed()
        onNodeWithContentDescription("error").assertIsDisplayed()
    }

    @Test
    fun `태그 길이 제한을 넘으면 에러 메시지와 아이콘을 표시한다`() = runComposeUiTest {
        setContent {
            var tags by mutableStateOf("")
            MaterialTheme {
                TagInputSection(
                    tags = tags,
                    onTagsChange = { tags = it },
                    errorMessage = validateTagError(tags),
                )
            }
        }

        onNode(hasSetTextAction()).performTextInput("abcdef")

        onNodeWithContentDescription("error").assertIsDisplayed()
    }

    @Test
    fun `태그 갯수 제한을 넘으면 에러 메시지와 아이콘을 표시한다`() = runComposeUiTest {
        setContent {
            var tags by mutableStateOf("")
            MaterialTheme {
                TagInputSection(
                    tags = tags,
                    onTagsChange = { tags = it },
                    errorMessage = validateTagError(tags),
                )
            }
        }

        onNode(hasSetTextAction()).performTextInput("태그1,태그2,태그3,태그4,태그5,태그6")

        onNodeWithContentDescription("error").assertIsDisplayed()
    }

    private fun validateTagError(tags: String): String? {
        return TagGroup.validate(tags)
    }
}
