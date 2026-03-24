package woowacourse.kanban.board.createtaskcard.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.kanban.board.taskcard.domain.Manager
import woowacourse.kanban.board.taskcard.domain.State
import woowacourse.kanban.board.taskcard.domain.Tags
import woowacourse.kanban.board.taskcard.domain.TaskCardData
import woowacourse.kanban.board.taskcard.domain.Title

class TaskCardDataInputState {

    var title by mutableStateOf("")

    var description by mutableStateOf("")

    var tags by mutableStateOf("")

    var selectedState by mutableStateOf(State.TODO)

    var selectedManager by mutableStateOf(Manager.DINO)

    fun getCard(): TaskCardData {
        val newCard = TaskCardData(
            title = title,
            description = description,
            tags = Tags(tags).extractTags(),
            state = selectedState,
            manager = selectedManager
        )

        return newCard
    }

    fun initializeState() {
        title = ""
        description = ""
        tags = ""
        selectedState = State.TODO
        selectedManager = Manager.DINO
    }
}
