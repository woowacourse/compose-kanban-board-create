package woowacourse.kanban.board.model

import woowacourse.kanban.board.model.TagGroup.Companion.MAXIMUM_TAG_COUNT

object TagValidator {
    private val TAG_FORMAT_REGEX = Regex("^[^,]+(\\s*,\\s*[^,]+)*$")

    fun validateCode(input: String): ValidationErrorCode? {
        if (input.isBlank()) return null
        if (!TAG_FORMAT_REGEX.matches(input)) return ValidationErrorCode.TAG_FORMAT_INVALID

        val tagTexts = input.split(",").map { it.trim() }
        if (tagTexts.size > MAXIMUM_TAG_COUNT || tagTexts.any { it.length > Tag.MAXIMUM_TAG_LENGTH }) {
            return ValidationErrorCode.TAG_LIMIT_INVALID
        }

        return null
    }

    fun validate(input: String): String? {
        return validateCode(input)?.let(ValidationMessages::messageOf)
    }
}
