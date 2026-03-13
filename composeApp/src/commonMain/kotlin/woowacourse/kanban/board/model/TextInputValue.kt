package woowacourse.kanban.board.model

import woowacourse.kanban.board.ComponentText

enum class TextInputValue(
    val label: String,
    val placeholder: String,
    val errorText: String = ""
) {
    TITLE(
        label = ComponentText.TITLE_LABEL,
        placeholder = ComponentText.TITLE_PLACEHOLDER,
        errorText = ComponentText.TITLE_ERROR
    ),
    DESCRIPTION(
        label = ComponentText.DESCRIPTION_LABEL,
        placeholder = ComponentText.DESCRIPTION_PLACEHOLDER,
    ),
    TAGS(
        label = ComponentText.TAG_LABEL,
        placeholder = ComponentText.TAG_PLACEHOLDER,
        errorText = ComponentText.TAG_ERROR
    )
}