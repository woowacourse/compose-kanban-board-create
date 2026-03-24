package woowacourse.kanban.board.model

data class Tag(val text: String) {
    init {
        if (text.isBlank()) {
            throw ValidationException(ValidationErrorCode.TAG_LIMIT_INVALID)
        }
        if (text.length > MAXIMUM_TAG_LENGTH) {
            throw ValidationException(ValidationErrorCode.TAG_LIMIT_INVALID)
        }
    }

    companion object {
        const val MAXIMUM_TAG_LENGTH = 5
    }
}
