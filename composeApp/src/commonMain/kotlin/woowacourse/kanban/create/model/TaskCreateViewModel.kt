package woowacourse.kanban.create.model

import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Stable
class TaskCreateViewModel {
    var titleInputValue by mutableStateOf("")
    var contentInputValue by mutableStateOf("")
    var tagInputValue by mutableStateOf("")

    var isTitleError by mutableStateOf(false)
    var isTagError by mutableStateOf(false)
    val isCreateError by derivedStateOf { isTitleError || isTagError }

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

    fun onTitleChange(input: String) {
        titleInputValue = input
        if (isTitleError) isTitleError = false
    }

    fun onContentChange(input: String) {
        contentInputValue = input
    }

    fun onTagChange(input: String) {
        tagInputValue = input
        if (isTagError) isTagError = false
    }

    fun onStatusSelect(index: Int) {
        selectedStatusIndex = index
    }

    fun onCoachSelect(index: Int) {
        selectedCoachIndex = index
    }

    fun onCardCreate() {
        isTitleError = titleInputValue.isEmpty()
        val tags = tagInputValue.split(",")
        isTagError = tags.size > 5 || tags.any { it.length > 5 }

        if (isTitleError) titleInputValue = ""
        if (isTagError) tagInputValue = ""
    }
}
