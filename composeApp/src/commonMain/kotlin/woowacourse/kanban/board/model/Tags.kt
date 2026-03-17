package woowacourse.kanban.board.model

import woowacourse.kanban.board.constant.TagsConst

data class Tags(val tags: String) {
    private fun extractTags() = tags.split(",").map { it.trim() }

    fun isNotValidTags(): Boolean {
        if (this.tags.isBlank()) return false

        val tags = extractTags()

        return tags.size > TagsConst.MAX_TAGS || tags.any { tag -> tag.isEmpty() || tag.length > TagsConst.TAG_MAX_TEXT_LENGTH }
    }
}