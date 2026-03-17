package woowacourse.kanban.board.study

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class LaunchedEffectTest {
    @Test
    fun launchedEffect() = runComposeUiTest {
        setContent {
            var flag by remember { mutableStateOf(true) }
            var triggerCount by remember { mutableStateOf(0) }

            LaunchedEffect(key1 = flag) {
                triggerCount++
            }

            Button(onClick = { flag = flag.not() }, modifier = Modifier.testTag("button")) {
                Text(text = triggerCount.toString())
            }
        }
        onNodeWithText("1").assertExists()
        onNodeWithTag("button").performClick()
        onNodeWithText("2").assertExists()
    }

    @Test
    fun `key1과 key2가 변경될 때마다 LaunchedEffect가 트리거되는지 확인`() = runComposeUiTest {
        setContent {
            var flag1 by remember { mutableStateOf(true) }
            var flag2 by remember { mutableStateOf(true) }
            var triggerCount by remember { mutableStateOf(0) }

            LaunchedEffect(key1 = flag1, key2 = flag2) {
                triggerCount++
            }

            Text(text = triggerCount.toString())
            Button(onClick = { flag1 = flag1.not() }, modifier = Modifier.testTag("button1")) {}
            Button(onClick = { flag2 = flag2.not() }, modifier = Modifier.testTag("button2")) {}
        }
        onNodeWithText("1").assertExists()
        onNodeWithTag("button1").performClick()
        onNodeWithText("2").assertExists()
        onNodeWithTag("button2").performClick()
        onNodeWithText("3").assertExists()
    }
}
