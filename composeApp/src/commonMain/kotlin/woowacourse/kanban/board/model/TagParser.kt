package woowacourse.kanban.board.model

import woowacourse.kanban.board.model.TagGroup.Companion.MAXIMUM_TAG_COUNT

object TagParser {
    private val TAG_FORMAT_REGEX = Regex("^[^,]+(\\s*,\\s*[^,]+)*$")

    fun parse(input: String): TagGroup {
        if (input.isBlank()) return TagGroup(emptyList())
        if (!TAG_FORMAT_REGEX.matches(input)) {
            throw ValidationException(ValidationErrorCode.TAG_FORMAT_INVALID)
        }

        val tagTexts = input.split(",").map { it.trim() }

        if (tagTexts.size > MAXIMUM_TAG_COUNT) {
            throw ValidationException(ValidationErrorCode.TAG_LIMIT_INVALID)
        }

        val tags = try {
            tagTexts.map { Tag(it) }
        } catch (_: ValidationException) {
            throw ValidationException(ValidationErrorCode.TAG_LIMIT_INVALID)
        }

        return TagGroup(tags)
    }
}
