package woowacourse.kanban.board.ui.createtaskcard

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Test
import woowacourse.kanban.board.testconstan.TestText
import woowacourse.kanban.board.constant.TagsConst
import woowacourse.kanban.board.constant.TestTags
import woowacourse.kanban.board.constant.TitleConst
import woowacourse.kanban.board.taskcard.domain.Tags
import woowacourse.kanban.board.taskcard.domain.Title
import woowacourse.kanban.board.createtaskcard.ui.LabelAndContent
import woowacourse.kanban.board.createtaskcard.ui.TextInput

@OptIn(ExperimentalTestApi::class)
class LabelAndContentTest {

    @Test
    fun `텍스트 필드에 입력된 텍스트는 화면에 표시된다` () = runComposeUiTest {
        setContent {
            var text by remember { mutableStateOf(TestText.EXAMPLE_TEXT) }

            LabelAndContent(
                label = "EXAMPLE",
                modifier = Modifier.testTag(TestTags.TITLE_INPUT),
            ) {
                TextInput(
                    value = text,
                    placeholder = TitleConst.TITLE_PLACEHOLDER,
                    onTextChange = { text = it },
                    singleLine = true
                )
            }
        }

        onNodeWithText(TestText.EXAMPLE_TEXT).assertExists()
    }

    @Test
    fun `제목이 비어있으면 에러 아이콘과 에러 텍스트가 표시된다`() = runComposeUiTest {
        setContent {
            var title by remember { mutableStateOf(TestText.BLANK_TEXT) }
            val isNotValidTitle by remember {
                derivedStateOf {
                    Title(title).isNotValidTitle()
                }
            }

            LabelAndContent(
                label = TitleConst.TITLE_LABEL,
                modifier = Modifier.testTag(TestTags.TITLE_INPUT),
            ) {
                TextInput(
                    value = title,
                    placeholder = TitleConst.TITLE_PLACEHOLDER,
                    onTextChange = { title = it },
                    singleLine = true,
                    isError = isNotValidTitle,
                    errorText = TitleConst.TITLE_ERROR
                )
            }
        }

        onNodeWithTag(TestTags.ERROR_ICON, useUnmergedTree = true).assertExists()
        onNodeWithTag(TestTags.ERROR_TEXT, useUnmergedTree = true).assertExists()
    }

    @Test
    fun `제목이 입력되면 에러 아이콘과 에러 텍스트가 표시되지 않는다`() = runComposeUiTest {
        setContent {
            var title by remember { mutableStateOf(TestText.TEST_TITLE) }
            val isNotValidTitle by remember {
                derivedStateOf {
                    Title(title).isNotValidTitle()
                }
            }

            LabelAndContent(
                label = TitleConst.TITLE_LABEL,
                modifier = Modifier.testTag(TestTags.TITLE_INPUT),
            ) {
                TextInput(
                    value = title,
                    placeholder = TitleConst.TITLE_PLACEHOLDER,
                    onTextChange = { title = it },
                    singleLine = true,
                    isError = isNotValidTitle,
                    errorText = TitleConst.TITLE_ERROR
                )
            }
        }

        onNodeWithTag(TestTags.ERROR_ICON, useUnmergedTree = true).assertDoesNotExist()
        onNodeWithTag(TestTags.ERROR_TEXT, useUnmergedTree = true).assertDoesNotExist()
    }

    @Test
    fun `입력된 태그가 없으면 에러 아이콘과 에러 텍스트가 표시되지 않는다`() = runComposeUiTest {
        setContent {
            var tags by remember { mutableStateOf(TestText.BLANK_TEXT) }
            val isNotValidTags by remember {
                derivedStateOf {
                    if(tags.isBlank()){
                        return@derivedStateOf false
                    }
                    Tags(tags).isNotValidTags()
                }
            }

            LabelAndContent(
                label = TagsConst.TAG_LABEL,
                modifier = Modifier.testTag(TestTags.TAGS_INPUT),
            ) {
                TextInput(
                    value = tags,
                    placeholder = TagsConst.TAG_PLACEHOLDER,
                    onTextChange = { tags = it },
                    singleLine = true,
                    supportingText = TagsConst.TAG_SUPPORTING,
                    isError = isNotValidTags,
                    errorText = TagsConst.TAG_ERROR,
                )
            }
        }

        onNodeWithTag(TestTags.ERROR_ICON, useUnmergedTree = true).assertDoesNotExist()
        onNodeWithTag(TestTags.ERROR_TEXT, useUnmergedTree = true).assertDoesNotExist()
    }

    @Test
    fun `입력된 태그의 개수가 5개 이상이면 에러 아이콘과 에러 텍스트를 표시한다`() = runComposeUiTest {
        setContent {
            var tags by remember { mutableStateOf(TestText.TOO_MANY_TAGS) }
            val isNotValidTags by remember {
                derivedStateOf {
                    if(tags.isBlank()){
                        return@derivedStateOf false
                    }
                    Tags(tags).isNotValidTags()
                }
            }

            LabelAndContent(
                label = TagsConst.TAG_LABEL,
                modifier = Modifier.testTag(TestTags.TAGS_INPUT),
            ) {
                TextInput(
                    value = tags,
                    placeholder = TagsConst.TAG_PLACEHOLDER,
                    onTextChange = { tags = it },
                    singleLine = true,
                    supportingText = TagsConst.TAG_SUPPORTING,
                    isError = isNotValidTags,
                    errorText = TagsConst.TAG_ERROR,
                )
            }
        }

        onNodeWithTag(TestTags.ERROR_ICON, useUnmergedTree = true).assertExists()
        onNodeWithTag(TestTags.ERROR_TEXT, useUnmergedTree = true).assertExists()
    }

    @Test
    fun `입력된 태그 중 5자 이상인 태그가 있으면 에러 아이콘과 에러 텍스트를 표시한다`() = runComposeUiTest {
        setContent {
            var tags by remember { mutableStateOf(TestText.TOO_LONG_TAG) }
            val isNotValidTags by remember {
                derivedStateOf {
                    if(tags.isBlank()){
                        return@derivedStateOf false
                    }
                    Tags(tags).isNotValidTags()
                }
            }

            LabelAndContent(
                label = TagsConst.TAG_LABEL,
                modifier = Modifier.testTag(TestTags.TAGS_INPUT),
            ) {
                TextInput(
                    value = tags,
                    placeholder = TagsConst.TAG_PLACEHOLDER,
                    onTextChange = { tags = it },
                    singleLine = true,
                    supportingText = TagsConst.TAG_SUPPORTING,
                    isError = isNotValidTags,
                    errorText = TagsConst.TAG_ERROR,
                )
            }
        }

        onNodeWithTag(TestTags.ERROR_ICON, useUnmergedTree = true).assertExists()
        onNodeWithTag(TestTags.ERROR_TEXT, useUnmergedTree = true).assertExists()
    }
}