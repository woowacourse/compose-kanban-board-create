package woowacourse.kanban.board.util

fun String.parseByComma(): List<String> = this.trim().split(",")
