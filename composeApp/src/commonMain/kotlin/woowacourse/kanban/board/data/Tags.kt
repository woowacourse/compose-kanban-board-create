package woowacourse.kanban.board.data

data class Tags(val tags: List<String> = emptyList()) {
    init {
        val validTags = tags.filter { it.isNotBlank() }
        require(validTags.size in 0..5) { "5자 이내의 태그를 최대 5개까지 등록할 수 있습니다." }
        validTags.forEach { tag -> require(tag.length in 1..5) { "5자 이내의 태그를 최대 5개까지 등록할 수 있습니다." } }
    }

    companion object {
        const val MIN_TAG_COUNT = 0
        const val MAX_TAG_COUNT = 5
        const val MIN_TAG_LETTER_COUNT = 1
        const val MAX_TAG_LETTER_COUNT = 5
        const val TAG_DELIMITER = ','

        fun validateTagsAndWordCount(value: String): String? {
            if (value.isBlank()) return null

            val tags = value.split(TAG_DELIMITER).map { it.trim() }
            if (tags.size !in MIN_TAG_COUNT..MAX_TAG_COUNT) return "태그는 5자 이내로 5개까지만 등록할 수 있습니다."
            tags.forEach { tag ->
                if (tag.length !in MIN_TAG_LETTER_COUNT..MAX_TAG_LETTER_COUNT) return "태그는 5자 이내로 5개까지만 등록할 수 있습니다."
            }
            return null
        }
    }
}
