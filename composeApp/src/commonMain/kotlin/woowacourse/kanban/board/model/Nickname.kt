package woowacourse.kanban.board.model

import woowacourse.kanban.board.constant.ERROR_TAG

@JvmInline
value class Nickname(val nickname: String) {
    init {
        require(nickname.isNotBlank()) { "$ERROR_TAG 닉네임이 비어있으면 안됩니다." }
    }
}
