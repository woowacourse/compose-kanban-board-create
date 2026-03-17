package woowacourse.kanban.board.study

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class LazyColumnTest {
    @Test
    fun column() = runComposeUiTest {
        setContent {
            Column {
                list.forEach {
                    Text(it, modifier = Modifier.testTag("text"))
                }
            }
        }
        onAllNodesWithTag("text")
            .assertCountEquals(10_000)
    }

    companion object {
        val list: List<String> = List(10_000) { it.toString() }
    }
}
