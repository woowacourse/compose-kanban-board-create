package woowacourse.kanban.create.model

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Stable
class TaskCreateState {
    var titleInputValue by mutableStateOf("")
    var contentInputValue by mutableStateOf("")
    var tagInputValue by mutableStateOf("")

    var isTitleError by mutableStateOf(false)
    var isTagError by mutableStateOf(false)

    var selectedStatusIndex by mutableIntStateOf(0)
    var selectedCoachIndex by mutableIntStateOf(0)

    val statuses = listOf(
        "To Do",
        "In Progress",
        "Done",
    )

    val names = listOf(
        "다이노",
        "페임스",
    )
}