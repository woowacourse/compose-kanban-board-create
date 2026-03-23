package woowacourse.kanban.board.domain

import kotlin.test.Test
import kotlin.test.assertEquals

class TasksTest {
    @Test
    fun `카드데이터 리스트를 받아서, 모든 카드의 개수를 셀 수 있다`() {
        // given
        val board = Tasks()
        board.addTask(Task.create(title = "제목", state = TaskState.TO_DO, managerName = "별터"))
        board.addTask(Task.create(title = "제목", state = TaskState.IN_PROGRESS, managerName = "별터"))
        val expected = 2

        // when
        val actual = board.countAllTasks()

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun `카드데이터 리스트를 받아서, 상태별 카드의 개수를 셀 수 있다`() {
        // given
        val board = Tasks()
        board.addTask(Task.create(title = "제목", state = TaskState.TO_DO, managerName = "별터"))
        board.addTask(Task.create(title = "제목", state = TaskState.IN_PROGRESS, managerName = "별터"))
        board.addTask(Task.create(title = "제목2", state = TaskState.IN_PROGRESS, managerName = "별터"))
        board.addTask(Task.create(title = "제목", state = TaskState.DONE, managerName = "별터"))
        val toDoExpected = 1
        val inProgressExpected = 2
        val doneExpected = 1

        // when
        val toDoActual = board.countTasksByState(TaskState.TO_DO)
        val inProgressActual = board.countTasksByState(TaskState.IN_PROGRESS)
        val doneActual = board.countTasksByState(TaskState.DONE)

        // then
        assertEquals(toDoExpected, toDoActual)
        assertEquals(inProgressExpected, inProgressActual)
        assertEquals(doneExpected, doneActual)
    }

    @Test
    fun `카드데이터 리스트를 받아서, 완료율을 계산한다`() {
        // given
        val tasks = Tasks()
        tasks.addTask(Task.create(title = "제목", state = TaskState.TO_DO, managerName = "별터"))
        tasks.addTask(Task.create(title = "제목", state = TaskState.IN_PROGRESS, managerName = "별터"))
        tasks.addTask(Task.create(title = "제목2", state = TaskState.IN_PROGRESS, managerName = "별터"))
        tasks.addTask(Task.create(title = "제목", state = TaskState.DONE, managerName = "별터"))
        val expected = 0.25f

        // when
        val actual = tasks.getCompleteRate()

        // then
        assertEquals(expected, actual)
    }
}
