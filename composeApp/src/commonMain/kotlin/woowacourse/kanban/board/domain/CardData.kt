package woowacourse.kanban.board.domain

import woowacourse.kanban.board.util.parseByComma

/**
 * Card 도메인 모델입니다.
 * 카드 생성 규칙을 적용합니다.
 * 생성은 [create] 팩토리 메서드로 수행합니다.
 */
class CardData private constructor(
    val title: String,
    val description: String,
    val tags: List<String>,
    val manager: String,
) {
    fun hasDescription(): Boolean = description.isNotBlank()

    fun hasTag(): Boolean = tags.isNotEmpty()

    companion object {
        private const val MAX_TAG_COUNT = 5
        private const val MAX_TAG_LENGTH = 5

        fun isValidTitle(rawText: String): TitleError {
            if (rawText.isBlank()) return TitleError.EMPTY
            return TitleError.NONE
        }

        fun isValidTag(rawText: String): TagError {
            if (rawText.isBlank()) return TagError.NONE

            val parsedText = parseByComma(rawText)

            return when {
                parsedText.any { it.isBlank() } -> TagError.INVALID_FORMAT
                parsedText.any { it.length > MAX_TAG_LENGTH } -> TagError.TOO_LONG
                parsedText.size > MAX_TAG_COUNT -> TagError.TOO_MANY
                else -> TagError.NONE
            }
        }

        /**
         * [CardData] 객체 생성 팩토리 메서드입니다.
         * @param title 필수 | 제목
         * @param description 본문
         * @param tags 태그
         * @param managerName 필수 | 계정명
         * @throws IllegalArgumentException 기능 요구사항을 충족하지 않을 경우 예외를 던집니다.
         */
        fun create(
            title: String,
            description: String,
            tags: List<String>,
            managerName: String,
        ): CardData {
            require(isValidTitle(title) == TitleError.NONE) { "[Card] 제목은 필수 입력 항목입니다." }
            require(managerName.isNotBlank()) { "[Card] 계정명은 필수 입력 항목입니다." }

            val normalizedTags = tags
                .map { it.trim() }
                .filter { it.isNotEmpty() }

            require(normalizedTags.size <= MAX_TAG_COUNT) { "[Card] 태그는 최대 ${MAX_TAG_COUNT}개까지 가능합니다." }
            require(normalizedTags.all { it.length <= MAX_TAG_LENGTH }) { "[Card] 태그는 최대 ${MAX_TAG_LENGTH}자까지 가능합니다." }

            return CardData(
                title = title,
                description = description,
                tags = normalizedTags,
                manager = managerName,
            )
        }
    }
}
