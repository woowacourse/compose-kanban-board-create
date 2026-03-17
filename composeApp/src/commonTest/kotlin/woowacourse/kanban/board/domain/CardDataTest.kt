package woowacourse.kanban.board.domain

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * [CardData] Unit 테스트 클래스입니다.
 */
class CardDataTest {
    @Test
    fun `제목이 공백만 있으면 생성할 수 없다`() {
        assertFailsWith<IllegalArgumentException> {
            CardData.create(
                title = "   ",
                description = "내용",
                tags = listOf("태그1"),
                managerName = "테스트 계정",
            )
        }
    }

    @Test
    fun `계정명이 공백만 있으면 생성할 수 없다`() {
        assertFailsWith<IllegalArgumentException> {
            CardData.create(
                title = "제목",
                description = "내용",
                tags = listOf("태그1"),
                managerName = "    ",
            )
        }
    }

    @Test
    fun `내용이 있으면 hasDescription 리턴 값은 true이다`() {
        val cardData = CardData.create(
            title = "제목",
            description = "내용",
            tags = emptyList(),
            managerName = "테스트 계정",
        )

        assertTrue(cardData.hasDescription())
    }

    @Test
    fun `내용이 공백이면 hasDescription 리턴 값은 false이다`() {
        val cardData = CardData.create(
            title = "제목",
            description = "   ",
            tags = emptyList(),
            managerName = "테스트 계정",
        )

        assertFalse(cardData.hasDescription())
    }

    @Test
    fun `태그의 앞뒤 공백은 제거된다`() {
        val cardData = CardData.create(
            title = "제목",
            description = "내용",
            tags = listOf(" 태그1 ", "  태그2  "),
            managerName = "테스트 계정",
        )

        assertEquals(listOf("태그1", "태그2"), cardData.tags)
    }

    @Test
    fun `공백으로만 구성된 태그는 제거된다`() {
        val cardData = CardData.create(
            title = "제목",
            description = "내용",
            tags = listOf("태그1", "   ", "", "  "),
            managerName = "테스트 계정",
        )

        assertEquals(listOf("태그1"), cardData.tags)
    }

    @Test
    fun `태그가 5개를 초과하면 생성할 수 없다`() {
        assertFailsWith<IllegalArgumentException> {
            CardData.create(
                title = "제목",
                description = "내용",
                tags = listOf("태그1", "태그2", "태그3", "태그4", "태그5", "태그6"),
                managerName = "테스트 계정",
            )
        }
    }

    @Test
    fun `태그 내용이 5글자를 초과하면 5글자까지만 유지된다`() {
        assertFailsWith<IllegalArgumentException> {
            CardData.create(
                title = "제목",
                description = "내용",
                tags = listOf("우아한테크코스", "안드로이드8기", "칸반보드리팩터링"),
                managerName = "테스트 계정",
            )
        }
    }

    @Test
    fun `태그가 있으면 hasTag 리턴 값은 true이다`() {
        val cardData = CardData.create(
            title = "제목",
            description = "내용",
            tags = listOf("태그1", "   "),
            managerName = "테스트 계정",
        )

        assertTrue(cardData.hasTag())
    }

    @Test
    fun `태그가 비어 있으면 hasTag 리턴 값은 false이다`() {
        val cardData = CardData.create(
            title = "제목",
            description = "내용",
            tags = listOf("   ", ""),
            managerName = "테스트 계정",
        )

        assertFalse(cardData.hasTag())
    }

    @Test
    fun `제목이 비어 있는 경우 TitleError_EMPTY 를 반환한다`() {
        // given
        val title = "   "
        // when
        val result = CardData.isValidTitle(title)
        // then
        assertEquals(TitleError.EMPTY, result)
    }

    @Test
    fun `잘못된 태그가 주어질 시 그에 따른 TagError 를 반환한다`() {
        // given
        val invalidFormatTags = "tag,,"
        val tooLongTags = "tagggg"
        val tooManyTags = "t1,t2,t3,t4,t5,t6"
        // when
        val result1 = CardData.isValidTag(invalidFormatTags)
        val result2 = CardData.isValidTag(tooLongTags)
        val result3 = CardData.isValidTag(tooManyTags)
        // then
        assertEquals(TagError.INVALID_FORMAT, result1)
        assertEquals(TagError.TOO_LONG, result2)
        assertEquals(TagError.TOO_MANY, result3)
    }
}
