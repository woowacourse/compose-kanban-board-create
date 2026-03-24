package woowacourse.kanban.board.unittest

import org.junit.Test
import woowacourse.kanban.board.taskcard.domain.Manager
import woowacourse.kanban.board.taskcard.domain.State
import woowacourse.kanban.board.taskcard.domain.TaskCardData
import woowacourse.kanban.board.kanbanboard.domain.TaskCardTable
import kotlin.test.assertEquals

class TaskCardCollectionTest {
    @Test
    fun `TODO 상태 태스크가 입력되면 todoTable에 저장된다`() {
        val taskCardTable = TaskCardTable()

        taskCardTable.addCard(TaskCardData(
            title = "title",
            description = "description",
            tags = listOf("tag1", "tag2"),
            state = State.TODO,
            manager = Manager.DINO
        ))

        assertEquals(1, taskCardTable.todoTaskCount)
        assertEquals(0, taskCardTable.inProgressTaskCount)
        assertEquals(0, taskCardTable.doneTaskCount)
    }

    @Test
    fun `IN_PROGRESS 상태 태스크가 입력되면 inProgressTable에 저장된다`() {
        val taskCardTable = TaskCardTable()

        taskCardTable.addCard(TaskCardData(
            title = "title",
            description = "description",
            tags = listOf("tag1", "tag2"),
            state = State.IN_PROGRESS,
            manager = Manager.DINO
        ))

        assertEquals(0, taskCardTable.todoTaskCount)
        assertEquals(1, taskCardTable.inProgressTaskCount)
        assertEquals(0, taskCardTable.doneTaskCount)
    }

    @Test
    fun `DONE 상태 태스크가 입력되면 doneTable에 저장된다`() {
        val taskCardTable = TaskCardTable()

        taskCardTable.addCard(TaskCardData(
            title = "title",
            description = "description",
            tags = listOf("tag1", "tag2"),
            state = State.DONE,
            manager = Manager.DINO
        ))

        assertEquals(0, taskCardTable.todoTaskCount)
        assertEquals(0, taskCardTable.inProgressTaskCount)
        assertEquals(1, taskCardTable.doneTaskCount)
    }

    @Test
    fun `입력된 태스크의 전채 개수를 알 수 있다`() {
        val taskCardTable = TaskCardTable()

        taskCardTable.addCard(TaskCardData(
            title = "title1",
            state = State.TODO,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title2",
            state = State.TODO,
            manager = Manager.FAMES
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title3",
            state = State.IN_PROGRESS,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title4",
            state = State.DONE,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title5",
            state = State.DONE,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title6",
            state = State.DONE,
            manager = Manager.DINO
        ))

        assertEquals(6, taskCardTable.allTaskCount)
    }

    @Test
    fun `여러 태스크 중 DONE 상태인 task의 개수를 알 수 있다`() {
        val taskCardTable = TaskCardTable()

        taskCardTable.addCard(TaskCardData(
            title = "title1",
            state = State.TODO,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title2",
            state = State.TODO,
            manager = Manager.FAMES
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title3",
            state = State.IN_PROGRESS,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title4",
            state = State.DONE,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title5",
            state = State.DONE,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title6",
            state = State.DONE,
            manager = Manager.DINO
        ))

        assertEquals(3, taskCardTable.doneTaskCount)
    }

    @Test
    fun `입력 된 태스크의 완료율을 알 수 있다`() {
        val taskCardTable = TaskCardTable()

        taskCardTable.addCard(TaskCardData(
            title = "title1",
            state = State.TODO,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title2",
            state = State.TODO,
            manager = Manager.FAMES
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title3",
            state = State.IN_PROGRESS,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title4",
            state = State.DONE,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title5",
            state = State.DONE,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title6",
            state = State.DONE,
            manager = Manager.DINO
        ))

        assertEquals(50, taskCardTable.ratioOfDoneInt)
    }

    @Test
    fun `완료된 태스크가 없으면 완료율은 0이디 `() {
        val taskCardTable = TaskCardTable()

        taskCardTable.addCard(TaskCardData(
            title = "title1",
            state = State.TODO,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title2",
            state = State.TODO,
            manager = Manager.FAMES
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title3",
            state = State.IN_PROGRESS,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title4",
            state = State.TODO,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title5",
            state = State.TODO,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title6",
            state = State.TODO,
            manager = Manager.DINO
        ))

        assertEquals(0, taskCardTable.ratioOfDoneInt)
    }

    @Test
    fun `모든 태스크가 완료되면 완료율은 100이다`() {
        val taskCardTable = TaskCardTable()

        taskCardTable.addCard(TaskCardData(
            title = "title1",
            state = State.DONE,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title2",
            state = State.DONE,
            manager = Manager.FAMES
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title3",
            state = State.DONE,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title4",
            state = State.DONE,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title5",
            state = State.DONE,
            manager = Manager.DINO
        ))
        taskCardTable.addCard(TaskCardData(
            title = "title6",
            state = State.DONE,
            manager = Manager.DINO
        ))

        assertEquals(100, taskCardTable.ratioOfDoneInt)
    }
}