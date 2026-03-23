package woowacourse.kanban.board.model

import woowacourse.kanban.board.util.ErrorMessage

data class Tag(val tags: List<String>) {
    init {
        require(tags.size <= MAXIMUM_TAG_COUNT) {
            ErrorMessage.tagCountExceeded(MAXIMUM_TAG_COUNT)
        }
        if (tags.isNotEmpty()) {
            require(tags.all { it.trim().isNotEmpty() }) { ErrorMessage.TAG_EMPTY }
            require(tags.all { it.trim().length <= MAXIMUM_TAG_LENGTH }) {
                ErrorMessage.tagTooLong(MAXIMUM_TAG_LENGTH)
            }
        }
    }

    fun isEmpty(): Boolean {
        return tags.isEmpty()
    }

    companion object {
        const val MAXIMUM_TAG_COUNT = 5
        const val MAXIMUM_TAG_LENGTH = 5
    }
}
