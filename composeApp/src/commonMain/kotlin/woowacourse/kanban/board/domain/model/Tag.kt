package woowacourse.kanban.board.domain.model

data class Tag(val content: String) {
    init {
        require(content.length in MIN_TAG_LENGTH..MAX_TAG_LENGTH) { TAG_LENGTH_WARNING_MESSAGE }
    }

    companion object {
        const val MIN_TAG_LENGTH = 1
        const val MAX_TAG_LENGTH = 5
        const val TAG_LENGTH_WARNING_MESSAGE = "최대 $MAX_TAG_LENGTH 글자까지 입력 가능합니다."
    }
}
