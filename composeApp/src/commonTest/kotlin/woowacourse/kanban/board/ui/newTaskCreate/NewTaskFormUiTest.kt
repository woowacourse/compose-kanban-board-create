package woowacourse.kanban.board.ui.newTaskCreate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import junit.framework.TestCase.assertEquals
import org.junit.Test
import woowacourse.kanban.newTaskCreate.component.NewTaskForm
import woowacourse.kanban.newTaskCreate.data.Assignee
import woowacourse.kanban.newTaskCreate.data.TaskStatus

class NewTaskFormUiTest {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `상태는 To Do, In Progress, Done 중 하나만 선텍힐 수 있다`() = runComposeUiTest {
        val assignees = listOf(
            Assignee(id = "dino", nickname = "다이노"),
            Assignee(id = "fames", nickname = "페임스"),
        )
        var selectedStatus by mutableStateOf(TaskStatus.TO_DO)
        setContent {
            NewTaskForm(
                title = "",
                onTitleChange = {},
                description = "",
                onDescriptionChange = {},
                tags = "",
                onTagsChange = {},
                selectedStatus = selectedStatus,
                onStatusChange = { selectedStatus = it },
                assignees = assignees,
                selectedAssigneeId = assignees.first().id,
                onAssignChange = {},
            )
        }

        onNodeWithText("In Progress").performClick()
        assertEquals(TaskStatus.IN_PROGRESS, selectedStatus)

        onNodeWithText("Done").performClick()
        assertEquals(TaskStatus.DONE, selectedStatus)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `담당자는 1명만 선택할 수 있다`() = runComposeUiTest {
        val assignees = listOf(
            Assignee(id = "dino", nickname = "다이노"),
            Assignee(id = "fames", nickname = "페임스"),
        )
        var selectedAssigneeId by mutableStateOf(assignees.first().id)

        setContent {
            NewTaskForm(
                title = "",
                onTitleChange = {},
                description = "",
                onDescriptionChange = {},
                tags = "",
                onTagsChange = {},
                selectedStatus = TaskStatus.TO_DO,
                onStatusChange = {},
                assignees = assignees,
                selectedAssigneeId = selectedAssigneeId,
                onAssignChange = { selectedAssigneeId = it },
            )
        }

        onNodeWithText("다이노").performClick()
        assertEquals("dino", selectedAssigneeId)

        onNodeWithText("페임스").performClick()
        assertEquals("fames", selectedAssigneeId)
    }
}
