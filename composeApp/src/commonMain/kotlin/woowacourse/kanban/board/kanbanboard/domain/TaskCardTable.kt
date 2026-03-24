package woowacourse.kanban.board.kanbanboard.domain

import androidx.compose.runtime.mutableStateListOf
import woowacourse.kanban.board.taskcard.domain.State
import woowacourse.kanban.board.taskcard.domain.TaskCardData

class TaskCardTable {
    val todoTable = mutableStateListOf<TaskCardData>()
    val inProgressTable = mutableStateListOf<TaskCardData>()
    val doneTable = mutableStateListOf<TaskCardData>()

    val allTaskCount: Int get() = todoTaskCount + inProgressTaskCount + doneTaskCount

    val todoTaskCount:Int get() = todoTable.size

    val inProgressTaskCount: Int get() = inProgressTable.size

    val doneTaskCount: Int get() = doneTable.size

    val ratioOfDoneInt: Int get() = if (allTaskCount == 0) 0 else (doneTaskCount * 100) / allTaskCount

    val ratioOfDoneFloat: Float get() = if (allTaskCount == 0) 0f else (doneTaskCount.toFloat() / allTaskCount)

    fun addCard(inputCard: TaskCardData) {
        when(inputCard.state){
            State.TODO -> todoTable.add(inputCard)
            State.IN_PROGRESS -> inProgressTable.add(inputCard)
            State.DONE -> doneTable.add(inputCard)
        }
    }
}
