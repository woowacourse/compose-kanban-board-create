package woowacourse.kanban.board.model

import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertIs

class TagTest {
    @Test
    fun `최대 5개의 태그를 생성할 수 있다`() {
        val tagTexts = listOf(
            "tag1",
            "tag2",
            "tag3",
            "tag4",
            "tag5",
        )
        val tags = Tag(tagTexts)
        assertIs<Tag>(tags)
        assert(tags.tags.size == 5)
    }

    @Test
    fun `태그 개수가 5개를 초과하면 에러가 발생한다`() {
        val tagTexts = listOf(
            "tag1",
            "tag2",
            "tag3",
            "tag4",
            "tag5",
            "tag6",
        )
        assertFailsWith<IllegalArgumentException> {
            Tag(tagTexts)
        }
    }

    @Test
    fun `태그 텍스트가 공란일 수 없다`() {
        assertFailsWith<IllegalArgumentException> {
            Tag(listOf(" "))
        }
    }

    @Test
    fun `태그 텍스트는 5글자 이하여야 한다`() {
        val tags = Tag(listOf("태그"))
        val tag = tags.tags.first()
        assert(tag == "태그")
        assert(tag.length <= Tag.MAXIMUM_TAG_LENGTH)
    }

    @Test
    fun `태그 텍스트가 5글자를 초과하면 에러가 발생한다`() {
        assertFailsWith<IllegalArgumentException> {
            Tag(listOf("엄청긴글자입니다"))
        }
    }
}
