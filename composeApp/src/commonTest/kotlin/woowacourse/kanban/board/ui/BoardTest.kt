package woowacourse.kanban.board.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import kotlinx.coroutines.launch
import kotlin.test.Test

@Composable
private fun BoardScreen() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        Button(
            onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("생성 완료")
                }
            },
            modifier = Modifier.padding(paddingValues),
        ) {
            Text("생성 버튼")
        }
    }
}

@OptIn(ExperimentalTestApi::class)
class BoardTest {
    @Test
    fun `생성 버튼을 누르면, 스낵바 알림이 뜬다`() = runComposeUiTest {
        // given
        setContent {
            BoardScreen()
        }

        // when
        onNodeWithText("생성 버튼").performClick()

        // then
        onNodeWithText("생성 완료").assertExists()
    }
}
