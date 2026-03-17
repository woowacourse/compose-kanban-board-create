package woowacourse.kanban.board.model.modal

data class Tags(val value: String) {

    fun getExtractedTags(): List<String> = value.split(",").map { it.trim() }

    companion object {
        const val MAX_TAGS = 5
        const val TAG_MAX_TEXT_LENGTH = 5

        fun isTagsValid(value: String): Boolean {
            if (value.isEmpty()) return true
            val extractedTags = value.split(",").map { it.trim() }
            return isTagsCountValid(extractedTags) && isTagTextValid(extractedTags)
        }

        private fun isTagsCountValid(extractedTags: List<String>): Boolean {
            return extractedTags.size <= MAX_TAGS
        }

        private fun isTagTextValid(extractedTags: List<String>): Boolean {
            return extractedTags.all { it.isNotBlank() && it.length <= TAG_MAX_TEXT_LENGTH }
        }
    }
}
