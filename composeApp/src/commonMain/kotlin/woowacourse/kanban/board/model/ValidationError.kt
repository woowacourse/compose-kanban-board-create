package woowacourse.kanban.board.model

enum class ValidationErrorCode {
    TITLE_REQUIRED,
    TAG_FORMAT_INVALID,
    TAG_LIMIT_INVALID,
}

object ValidationMessages {
    const val TITLE_REQUIRED = "제목을 입력해 주세요."
    const val TAG_FORMAT_INVALID = "태그 형식이 올바르지 않습니다."
    const val TAG_LIMIT_INVALID = "태그는 5자 이내로 5개까지만 등록할 수 있습니다."

    fun messageOf(code: ValidationErrorCode): String =
        when (code) {
            ValidationErrorCode.TITLE_REQUIRED -> TITLE_REQUIRED
            ValidationErrorCode.TAG_FORMAT_INVALID -> TAG_FORMAT_INVALID
            ValidationErrorCode.TAG_LIMIT_INVALID -> TAG_LIMIT_INVALID
        }
}

class ValidationException(
    val code: ValidationErrorCode,
) : IllegalArgumentException(ValidationMessages.messageOf(code))
