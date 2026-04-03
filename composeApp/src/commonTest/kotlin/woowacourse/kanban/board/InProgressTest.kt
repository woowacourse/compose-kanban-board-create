package woowacourse.kanban.board

import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertThrows
import woowacourse.kanban.board.model.InProgress
import woowacourse.kanban.board.model.Task
import woowacourse.kanban.board.model.TaskStatus
import kotlin.test.Test

class InProgressTest {
    @Test
    fun `담당자가 없는 경우 In Progress 생성 불가`() {
        val task = Task("수업하기")
        assertThrows(IllegalStateException::class.java) {
            InProgress(task)
        }
    }

    @Test
    fun `In Progress는 TODO, Review로 전이 가능`() {
        val task = Task("수업하기", assignee = "레아")
        val inProgress = InProgress(task)
        val todo = inProgress.moveTo(TaskStatus.TODO)
        val review = inProgress.moveTo(TaskStatus.REVIEW)
        assertThat(todo.task.title).isEqualTo("수업하기")
        assertThat(review.task.title).isEqualTo("수업하기")
    }

    @Test
    fun `In Progress는 Done로 전이 불가능`() {
        val task = Task("수업하기", assignee = "레아")
        val inProgress = InProgress(task)
        assertThrows(IllegalStateException::class.java) {
            inProgress.moveTo(TaskStatus.DONE)
        }
    }
}
