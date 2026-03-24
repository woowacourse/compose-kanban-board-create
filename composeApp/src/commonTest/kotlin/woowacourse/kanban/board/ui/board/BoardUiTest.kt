package woowacourse.kanban.board.ui.board

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test
import woowacourse.kanban.board.component.KanbanBoard
import woowacourse.kanban.board.data.KanbanBoardSampleData
import woowacourse.kanban.newTaskCreate.data.Task
import woowacourse.kanban.newTaskCreate.data.TaskStatus

@OptIn(ExperimentalTestApi::class)
class BoardUiTest {
    @Test
    fun `초기 렌더링 시 헤더 제목과 완료율이 표시된다`() = runComposeUiTest {
        setContent {
            KanbanBoard(
                tasks = KanbanBoardSampleData.Tasks,
            )
        }

        onNodeWithText("Compose Desk 칸반 보드").assertExists()
        onNodeWithText("완료율: 50% (3/6)").assertExists()
        onNodeWithTag("open_create_task_button").assertExists()
    }

    @Test
    fun `초기 렌더링 시 세 개의 진행 상태 컬럼이 표시된다`() = runComposeUiTest {
        setContent {
            KanbanBoard(
                tasks = KanbanBoardSampleData.Tasks,
            )
        }

        onNodeWithText("To Do").assertExists()
        onNodeWithText("In Progress").assertExists()
        onNodeWithText("Done").assertExists()
    }

    @Test
    fun `할 일 컬럼의 샘플 태스크가 표시된다`() = runComposeUiTest {
        setContent {
            KanbanBoard(
                tasks = KanbanBoardSampleData.Tasks,
            )
        }

        onNodeWithText("LazyColumn 컴포넌트 구현").assertExists()
        onNodeWithText("Side-effect API 학습").assertExists()
    }

    @Test
    fun `완료 컬럼의 샘플 태스크가 표시된다`() = runComposeUiTest {
        setContent {
            KanbanBoard(
                tasks = KanbanBoardSampleData.Tasks,
            )
        }

        onNodeWithText("리컴포지션 최적화").assertExists()
        onNodeWithText("Mock API 설정").assertExists()
        onNodeWithText("Drag & Drop 기능 구현").assertExists()
    }

    @Test
    fun `DONE 태스크가 추가된 상태를 주입하면 완료율과 DONE 개수가 반영된다`() = runComposeUiTest {
        val tasksWithOneMoreDone = KanbanBoardSampleData.Tasks + Task(
            taskTitle = "DONE 추가 태스크",
            assigneeId = "dino",
            status = TaskStatus.DONE,
        )

        setContent {
            KanbanBoard(
                tasks = tasksWithOneMoreDone,
            )
        }

        onNodeWithTag("completion_rate_text").assertTextEquals("완료율: 57% (4/7)")
        onNodeWithTag("done_count_badge").assertTextEquals("4")
    }

    @Test
    fun `TO DO 태스크가 추가된 상태를 주입하면 To Do 개수와 완료율이 반영된다`() = runComposeUiTest {
        val tasksWithOneMoreTodo = KanbanBoardSampleData.Tasks + Task(
            taskTitle = "TO DO 추가 태스크",
            assigneeId = "dino",
            status = TaskStatus.TO_DO,
        )

        setContent {
            KanbanBoard(
                tasks = tasksWithOneMoreTodo,
            )
        }

        onNodeWithTag("todo_count_badge").assertTextEquals("3")
        onNodeWithTag("done_count_badge").assertTextEquals("3")
        onNodeWithTag("completion_rate_text").assertTextEquals("완료율: 42% (3/7)")
    }
}
