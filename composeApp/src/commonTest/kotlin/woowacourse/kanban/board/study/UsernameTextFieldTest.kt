package woowacourse.kanban.board.study

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.tooling.preview.Preview
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class UsernameTextFieldTest {

    @Composable
    fun UsernameTextField(username: String, onUsernameChange: (String) -> Unit) {
        val isError = username.length !in 2..5

        TextField(
            value = username,
            onValueChange = { onUsernameChange(it) },
            isError = isError,
            supportingText = {
                if (isError) Text("이름은 2~5자여야 합니다", modifier = Modifier.testTag("error"))
            },
        )
    }

    @Test
    fun `이름에 문자열을 입력하면 예외가 발생하지 않는다`() = runComposeUiTest {
        // given: 이름 초깃값은 빈 문자열이다.
        var username by mutableStateOf("")

        setContent {
            val isError = false
            TextField(
                value = username,
                onValueChange = { username = it },
                isError = isError,
                supportingText = {
                    if (isError) Text("에러 메시지")
                },
            )
        }
        // when: 사용자가 "김컴포즈"라는 문자열을 입력한다.
        username = "김컴포즈"
        // then: 에러 메세지가 보여져서는 안 된다.
        onNodeWithText("에러 메시지").assertDoesNotExist()
    }

    @Test
    fun `test2`() = runComposeUiTest {
        // given: 이름 초깃값은 빈 문자열이다.
        var username by mutableStateOf("")
        setContent {
            UsernameTextField(username, { username = it })
        }
        // when: 사용자가 "김컴포즈입니다"라는 문자열을 입력한다.
        username = "김컴포즈입니다."
        // then: "이름은 2~5자여야 합니다" 에러 메세지가 노출된다.
        onNodeWithTag("error", useUnmergedTree = true).assertExists()
    }
}