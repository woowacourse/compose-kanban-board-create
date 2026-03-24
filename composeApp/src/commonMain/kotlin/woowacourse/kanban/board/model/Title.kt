package woowacourse.kanban.board.model

@JvmInline
value class Title(val text: String) {
    init {
        if (text.isBlank()) {
            throw ValidationException(ValidationErrorCode.TITLE_REQUIRED)
        }
    }

    companion object {
        fun validateCode(input: String): ValidationErrorCode? =
            if (input.isBlank()) ValidationErrorCode.TITLE_REQUIRED else null

        fun validate(input: String): String? =
            validateCode(input)?.let(ValidationMessages::messageOf)
    }
}
