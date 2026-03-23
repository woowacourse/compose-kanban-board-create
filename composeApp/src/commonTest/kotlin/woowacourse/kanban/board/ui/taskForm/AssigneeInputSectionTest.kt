package woowacourse.kanban.board.ui.taskForm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test
import woowacourse.kanban.board.model.Assignee

@OptIn(ExperimentalTestApi::class)
class AssigneeInputSectionTest {
    val assignees = listOf(
        Assignee("다이노"),
        Assignee("페임스")
    )

    @Test
    fun `화면에 담당자가 모두 표시된다`() = runComposeUiTest {
        setContent {
            AssigneeInputSection(
                assignees = assignees,
                selected = assignees.first(),
                onSelect = {},
            )
        }

        onNodeWithText("다이노").assertIsDisplayed()
        onNodeWithText("페임스").assertIsDisplayed()
    }

    @Test
    fun `기본값으로 첫 번째 담당자가 선택되어 있다`() = runComposeUiTest {
        var selected by mutableStateOf(assignees.first())

        setContent {
            AssigneeInputSection(
                assignees = assignees,
                selected = selected,
                onSelect = { selected = it },
            )
        }

        runOnIdle {
            assert(selected == assignees.first())
        }
    }

    @Test
    fun `두 번째 담당자를 선택하면 선택값이 변경된다`() = runComposeUiTest {
        var selected by mutableStateOf(assignees.first())

        setContent {
            AssigneeInputSection(
                assignees = assignees,
                selected = selected,
                onSelect = { selected = it },
            )
        }

        onNodeWithText("페임스").performClick()

        runOnIdle {
            assert(selected == assignees[1])
        }
    }
}
