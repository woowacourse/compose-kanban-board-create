package woowacourse.kanban.board

import org.assertj.core.api.Assertions.assertThat
import woowacourse.kanban.board.model.Review
import woowacourse.kanban.board.model.Task
import woowacourse.kanban.board.model.TaskStates
import woowacourse.kanban.board.model.Todo
import kotlin.test.Test

class TaskStatesTest {

    @Test
    fun `TODO는 태스크 삭제 가능`() {
        val taskState = Todo(Task("수업하기"))
        val taskStates = TaskStates(listOf(taskState))
        val newTaskStates = taskStates.deleteTask(taskState)
        assertThat(newTaskStates).isEmpty()
    }

    @Test
    fun `Review는 태스크 삭제 불가능`() {
        val taskState = Review(Task("수업하기", assignee = "레아"))
        val taskStates = TaskStates(listOf(taskState))
        val newTaskStates = taskStates.deleteTask(taskState)
        assertThat(newTaskStates).isNotEmpty()
    }

}
