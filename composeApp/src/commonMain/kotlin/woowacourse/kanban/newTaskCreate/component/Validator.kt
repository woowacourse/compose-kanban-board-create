package woowacourse.kanban.newTaskCreate.component

import woowacourse.kanban.newTaskCreate.data.NewTaskFormData

enum class NewTaskFormError {
    TITLE_EMPTY, // 제목이 비어있는 경우
    TAG_FORMAT_INVALID, // 태그 형식이 유효하지 않을 경우
    TAG_LIMIT_EXCEEDED // 태그의 글자수나 태그의 갯수가 유효범위를 벗어난 경우
}
fun NewTaskFormError.message(): String = when (this) {
    NewTaskFormError.TITLE_EMPTY -> "제목을 입력해 주세요."
    NewTaskFormError.TAG_FORMAT_INVALID -> "태그 형식이 올바르지 않습니다."
    NewTaskFormError.TAG_LIMIT_EXCEEDED -> "태그는 5자 이내로 5개까지만 등록할 수 있습니다."
}
fun validateTitle(value: String?): NewTaskFormError? {
    if (value.isNullOrBlank()) return NewTaskFormError.TITLE_EMPTY
    return null
}

fun validateTags(value: String): NewTaskFormError? {
    // 태그의 형식이 유효하지 않은 경우
    if (value.isBlank()) return null
    val rawTags = value.split(",")

    if (rawTags.any { it.isBlank() }) {
        return NewTaskFormError.TAG_FORMAT_INVALID
    }

    val tags = rawTags.map { it.trim() }

    // 태그의 글자수와 갯수가 유효범위를 벗어나는 경우
    if (tags.size > 5) {
        return NewTaskFormError.TAG_LIMIT_EXCEEDED
    }

    if (tags.any { it.length > 5 }) {
        return NewTaskFormError.TAG_LIMIT_EXCEEDED
    }
    return null
}

fun validateNewTaskForm(form: NewTaskFormData): List<NewTaskFormError> = buildList {
    validateTitle(
        form.title
    )?.let(::add)
    validateTags(
        form.tags
    )?.let(::add)
}

fun validateDescription(value: String): String? = null
