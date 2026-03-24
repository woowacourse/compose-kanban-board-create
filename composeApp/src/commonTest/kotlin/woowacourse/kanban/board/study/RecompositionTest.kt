package woowacourse.kanban.board.study

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class RecompositionTest {

    private var username by mutableStateOf("")
    private var label by mutableStateOf("라벨")
    private var count = 0

    @Before
    fun setup() {
        username = ""
        label = "라벨"
        count = 0
    }

    @Composable
    private fun UsernameTextField(
        username: String,
        label: String,
    ) {
        val usernameLengthError = (username.length !in 2..5).also { count++ }
        TextField(
            value = username,
            label = { Text(label) },
            onValueChange = { },
            isError = usernameLengthError,
        )
    }

    @Composable
    private fun UsernameTextFieldWithRemember(
        username: String,
        label: String,
    ) {
        val usernameLengthError = remember(username) {
            (username.length !in 2..5).also { count++ }
        }
        TextField(
            value = username,
            label = { Text(label) },
            onValueChange = { },
            isError = usernameLengthError,
        )
    }
}
