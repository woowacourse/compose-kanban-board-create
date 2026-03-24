package woowacourse.kanban.board.study

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class UsernameTextFieldTest {
    companion object {
        private const val USERNAME_FIELD_TAG = "username_text_field"
        private const val ERROR_TEXT_TAG = "error"
        private const val ERROR_MESSAGE = "이름은 2~5자여야 합니다."
    }

    @Test
    fun `유효한 길이의 이름을 입력하면 에러 메시지가 노출되지 않는다`() = runComposeUiTest {
        setContent {
            var username by mutableStateOf("김컴포즈")
            val isError = username.isNotEmpty() && username.length !in 2..5

            TextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier.testTag(USERNAME_FIELD_TAG),
                isError = isError,
                supportingText = {
                    if (isError) {
                        Text(ERROR_MESSAGE, modifier = Modifier.testTag(ERROR_TEXT_TAG))
                    }
                }
            )
        }

        onNodeWithTag(ERROR_TEXT_TAG, useUnmergedTree = true).assertDoesNotExist()
    }

    @Test
    fun `유효하지 않은 길이의 이름을 입력하면 에러 메시지가 노출된다`() = runComposeUiTest {
        setContent {
            var username by mutableStateOf("abcdef")
            val isError = username.isNotEmpty() && username.length !in 2..5

            TextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier.testTag(USERNAME_FIELD_TAG),
                isError = isError,
                supportingText = {
                    if (isError) {
                        Text(ERROR_MESSAGE, modifier = Modifier.testTag(ERROR_TEXT_TAG))
                    }
                }
            )
        }

        onNodeWithTag(ERROR_TEXT_TAG, useUnmergedTree = true).assertExists()
    }
}
