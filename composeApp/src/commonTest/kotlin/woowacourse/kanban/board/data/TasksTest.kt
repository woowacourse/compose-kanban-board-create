package woowacourse.kanban.board.data

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TasksTest {
    @Test
    fun `태스크를 추가하면 전체 태스크 수가 증가한다`() {
        val tasks = Tasks(mutableListOf())

        tasks.addNewTask("제목", "설명", "태그1, 태그2", TaskStatus.DONE, Nickname("다이노"))

        assertEquals(1, tasks.tasksSize())
    }

    @Test
    fun `태그를 콤마로 구분하여 입력하면 공백이 제거된 리스트로 변환된다`() {
        val taskList = mutableListOf<Task>()
        val tasks = Tasks(taskList)

        tasks.addNewTask("제목", "설명", " 태그1 , 태그2 ", TaskStatus.TO_DO, Nickname("다이노"))

        val addedTask = taskList[0]
        assertEquals(listOf("태그1", "태그2"), addedTask.tags.tags)
    }

    @Test
    fun `완료된 태스크 비율이 올바르게 계산된다`() {
        val tasks = Tasks(mutableListOf())
        tasks.addNewTask("T1", "D1", "", TaskStatus.DONE, Nickname("다이노"))    // 완료
        tasks.addNewTask("T2", "D2", "", TaskStatus.TO_DO, Nickname("다이노"))   // 미완료

        val ratio = tasks.calculateDoneTasksRatio()

        assertEquals(50.0, ratio)
    }

    @Test
    fun `완료율 계산 시 반올림이 적용된다`() {
        val tasks = Tasks(mutableListOf())
        tasks.addNewTask("T1", "", "", TaskStatus.DONE, Nickname("다이노"))
        tasks.addNewTask("T2", "", "", TaskStatus.DONE, Nickname("다이노"))
        tasks.addNewTask("T3", "", "", TaskStatus.TO_DO, Nickname("다이노"))

        assertEquals(67.0, tasks.calculateDoneTasksRatio())
    }

    @Test
    fun `태스크가 없을 때 완료율은 0이다`() {
        val tasks = Tasks(mutableListOf())

        assertEquals(0.0, tasks.calculateDoneTasksRatio())
    }

    @Test
    fun `상태별로 태스크를 올바르게 필터링한다`() {
        val tasks = Tasks(mutableListOf())
        tasks.addNewTask("T1", "", "", TaskStatus.TO_DO, Nickname("다이노"))
        tasks.addNewTask("T2", "", "", TaskStatus.IN_PROGRESS, Nickname("다이노"))
        tasks.addNewTask("T3", "", "", TaskStatus.DONE, Nickname("다이노"))

        assertEquals(1, tasks.filterTasksByStatus(TaskStatus.TO_DO).size)
        assertEquals(1, tasks.filterTasksByStatus(TaskStatus.IN_PROGRESS).size)
        assertEquals(1, tasks.filterTasksByStatus(TaskStatus.IN_PROGRESS).size)

        assertEquals("T1", tasks.filterTasksByStatus(TaskStatus.TO_DO)[0].taskTitle.titleText)
        assertEquals("T2", tasks.filterTasksByStatus(TaskStatus.IN_PROGRESS)[0].taskTitle.titleText)
        assertEquals("T3", tasks.filterTasksByStatus(TaskStatus.DONE)[0].taskTitle.titleText)
    }

    @Test
    fun `태그가 빈 문자열이면 빈 리스트로 저장된다`() {
        val taskList = mutableListOf<Task>()
        val tasks = Tasks(taskList)

        tasks.addNewTask("제목", "", "  ", TaskStatus.TO_DO, Nickname("다이노"))

        assertTrue(taskList[0].tags.tags.isEmpty())
    }
}
