package woowacourse.kanban.board

import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertThrows
import woowacourse.kanban.board.model.InProgress
import woowacourse.kanban.board.model.Task
import woowacourse.kanban.board.model.TaskStatus
import woowacourse.kanban.board.model.Todo
import kotlin.test.Test

class TodoTest {

    @Test
    fun `TODO는 In Progress로 전이 가능`() {
        val task = Task("수업하기", assignee = "레아")
        val todo = Todo(task)
        val inProgress = todo.moveTo(TaskStatus.IN_PROGRESS)
        assertThat(inProgress.task.title).isEqualTo("수업하기")
    }

    @Test
    fun `TODO는 담당자가 없는 경우 In Progress로 전이 불가능`() {
        val task = Task("수업하기")
        val todo = Todo(task)
        assertThrows(IllegalStateException::class.java) {
            todo.moveTo(TaskStatus.IN_PROGRESS)
        }
    }

    @Test
    fun `TODO는 Review, Done로 전이 불가능`() {
        val task = Task("수업하기")
        val todo = Todo(task)
        assertThrows(IllegalStateException::class.java) {
            todo.moveTo(TaskStatus.REVIEW)
        }
        assertThrows(IllegalStateException::class.java) {
            todo.moveTo(TaskStatus.DONE)
        }
    }
}
