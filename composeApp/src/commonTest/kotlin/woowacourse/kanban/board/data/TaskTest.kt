package woowacourse.kanban.board.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test

class TaskTest {
    @Test
    fun `빈 제목이 들어오면 Task를 생성하지 않는다`() {
        val emptyTitle = ""
        val emptyTitle2 = "  "

        assertThrows(IllegalArgumentException::class.java) {
            Task(
                taskTitle = Title(emptyTitle),
                nickname = Nickname("사무엘"),
            )
        }
        assertThrows(IllegalArgumentException::class.java) {
            Task(
                taskTitle = Title(emptyTitle2),
                nickname = Nickname("사무엘"),
            )
        }
    }

    @Test
    fun `닉네임이 비어있다면 Task를 생성하지 않는다`() {
        val emptyName = ""
        val emptyName2 = "  "

        assertThrows(IllegalArgumentException::class.java) { Task(taskTitle = Title("title"), nickname = Nickname(emptyName)) }
        assertThrows(IllegalArgumentException::class.java) { Task(taskTitle = Title("title"), nickname = Nickname(emptyName2)) }
    }

    @Test
    fun `태그가 비어있거나 형식에 맞을 경우 Task를 생성한다`() {
        val task = Task(
            taskTitle = Title("title"),
            tags = Tags(emptyList()),
            nickname = Nickname("samuel"),
        )

        assertThat(task.taskTitle.titleText).isEqualTo("title")
    }
}
