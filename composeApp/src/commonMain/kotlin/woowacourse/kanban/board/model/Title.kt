package woowacourse.kanban.board.model

data class Title(
    val title: String
) {
    fun isNotValidTitle() = title.isBlank()
}