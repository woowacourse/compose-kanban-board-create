package woowacourse.kanban.board.taskcard.domain

import woowacourse.kanban.board.constant.TagsConst

data class Tags(val values: String) {
    fun extractTags() = values.split(",").map { it.trim() }

    fun isNotValidTags(): Boolean {
        if (this.values.isEmpty()) return false

        val extractedTags = extractTags()

        return extractedTags.size > TagsConst.MAX_TAGS || extractedTags.any { tag -> tag.isBlank() || tag.length > TagsConst.TAG_MAX_TEXT_LENGTH }
    }
}
