package woowacourse.kanban.board.domain.model

data class User(val name: String, val profileImg: String? = null) {
    init {
        require(name.isNotBlank()) {
            USER_EMPTY_WARNING_MESSAGE
        }
    }

    companion object {

        val managersList = listOf(
            User("디노"),
            User("제임스"),
        )

        const val USER_EMPTY_WARNING_MESSAGE = "이름은 공백일 수 없습니다."
    }
}
