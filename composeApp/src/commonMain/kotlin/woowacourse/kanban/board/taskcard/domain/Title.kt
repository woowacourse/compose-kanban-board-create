package woowacourse.kanban.board.taskcard.domain

data class Title(
    val value: String
) {
    fun isNotValidTitle() = value.isBlank()
}
