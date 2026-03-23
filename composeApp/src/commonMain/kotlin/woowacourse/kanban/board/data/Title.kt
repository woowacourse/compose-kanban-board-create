package woowacourse.kanban.board.data

data class Title(val titleText: String) {
    init {
        require(validateTitle(titleText).isNullOrEmpty()) { "제목을 입력해 주세요." }
    }

    companion object {
        fun validateTitle(value: String?): String? {
            if (value.isNullOrEmpty() || value.isBlank()) return "제목을 입력해 주세요."
            return null
        }
    }
}
