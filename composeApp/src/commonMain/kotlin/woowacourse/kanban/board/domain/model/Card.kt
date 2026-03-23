package woowacourse.kanban.board.domain.model

// user를 nullable로 변경 -> 탈퇴한 사용자인 경우를 가정
data class Card(val title: String?, val content: String? = null, val tags: List<Tag> = emptyList(), val user: User?) {
    init {
        require(tags.size in 0..MAX_TAG_SIZE) { TAG_SIZE_WARNING_MESSAGE }
        require(!title.isNullOrBlank() || !content.isNullOrBlank() || tags.isNotEmpty()) { TAG_EMPTY_WARNING_MESSAGE }
    }

    companion object {
        const val MAX_TAG_SIZE = 5
        const val TAG_SIZE_WARNING_MESSAGE = "태그는 최대 ${MAX_TAG_SIZE}개까지 입력 가능합니다."
        const val TAG_EMPTY_WARNING_MESSAGE = "입력한 내용이 없습니다."
    }
}
