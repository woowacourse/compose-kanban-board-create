package woowacourse.kanban.board.model

class Tags(val tags: List<String> = listOf()) {
    init {
        require(tags.size <= MAX_TAG_SIZE) { "..." }
    }

    companion object {
        private const val MAX_TAG_SIZE = 5
    }
}