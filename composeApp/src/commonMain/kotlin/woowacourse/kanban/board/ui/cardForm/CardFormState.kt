package woowacourse.kanban.board.ui.cardForm

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.kanban.board.domain.CardData
import woowacourse.kanban.board.domain.TagError
import woowacourse.kanban.board.domain.TaskState
import woowacourse.kanban.board.domain.TitleError

class CardFormState {
    var taskTitle by mutableStateOf("")
    var state by mutableStateOf(TaskState.TO_DO)
    var managerName by mutableStateOf("다이노")
    var description by mutableStateOf("")
    var tempTags by mutableStateOf("")
    val titleError: TitleError
        get() = CardData.isValidTitle(taskTitle)
    val tagError: TagError
        get() = CardData.isValidTag(tempTags)
    val tagInfoText: String
        get() = getTagInfoMessage(tagError)
    val createEnabled by derivedStateOf {
        titleError == TitleError.NONE && tagError == TagError.NONE
    }

    private fun getTagInfoMessage(tagError: TagError): String {
        return when (tagError) {
            TagError.NONE -> "5자 이내의 태그를 최대 5개까지 등록할 수 있습니다."
            TagError.INVALID_FORMAT -> "태그 형식이 올바르지 않습니다."
            TagError.TOO_MANY, TagError.TOO_LONG -> "태그는 5자 이내로 5개까지만 등록할 수 있습니다."
        }
    }
}
