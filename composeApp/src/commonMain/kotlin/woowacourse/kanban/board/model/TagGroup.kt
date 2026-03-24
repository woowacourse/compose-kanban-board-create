package woowacourse.kanban.board.model

data class TagGroup(val tags: List<Tag>) {
    init {
        if (tags.size > MAXIMUM_TAG_COUNT) {
            throw ValidationException(ValidationErrorCode.TAG_LIMIT_INVALID)
        }
    }

    fun isEmpty(): Boolean = tags.isEmpty()

    companion object {
        const val MAXIMUM_TAG_COUNT = 5
    }
}
