package woowacourse.kanban.board.component.taskboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.kanban.board.component.taskcard.KanbanCardForm
import woowacourse.kanban.board.component.taskmodal.ModalCreateFormState

class KanbanBoardState() {
    var showDialog by mutableStateOf(false)
    val taskList = mutableStateListOf<KanbanCardForm>()
    val modalState = ModalCreateFormState()

    val taskGroup
        get() = taskList.groupBy { it.status }

    fun addTask(task: KanbanCardForm) {
        taskList.add(task)
    }
}


