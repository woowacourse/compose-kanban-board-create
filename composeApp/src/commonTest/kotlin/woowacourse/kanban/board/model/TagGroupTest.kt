package woowacourse.kanban.board.model

import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertIs

class TagGroupTest {
    @Test
    fun `최대 5개의 태그를 생성할 수 있다`() {
        val tags = listOf(
            Tag("tag1"),
            Tag("tag2"),
            Tag("tag3"),
            Tag("tag4"),
            Tag("tag5")
        )
        val tagGroup = TagGroup(tags)
        assertIs<TagGroup>(tagGroup)
        val items = tagGroup as TagGroup
        assert(items.tags.size == 5)
    }

    @Test
    fun `태그 개수가 5개를 초과하면 에러가 발생한다`() {
        val tags = listOf(
            Tag("tag1"),
            Tag("tag2"),
            Tag("tag3"),
            Tag("tag4"),
            Tag("tag5"),
            Tag("tag6")
        )
        assertFailsWith<IllegalArgumentException> {
            TagGroup(tags)
        }
    }

    @Test
    fun `parse 함수로 빈 문자열을 파싱하면 빈 태그 그룹을 반환한다`() {
        val result = TagGroup.parse("")
        assert(result.isEmpty())
    }

    @Test
    fun `parse 함수로 유효한 태그 문자열을 파싱할 수 있다`() {
        val result = TagGroup.parse("버그, 긴급")
        assert(result.tags.size == 2)
        assert(result.tags[0].text == "버그")
        assert(result.tags[1].text == "긴급")
    }

    @Test
    fun `parse 함수로 공백이 있는 태그 문자열을 파싱할 수 있다`() {
        val result = TagGroup.parse("버그 , 긴급 , 수정")
        assert(result.tags.size == 3)
    }

    @Test
    fun `parse 함수로 형식이 잘못된 문자열을 파싱하면 실패한다`() {
        val exception = assertFailsWith<ValidationException> {
            TagGroup.parse("버그,  , 긴급")
        }
        assert(exception.message == ValidationMessages.TAG_FORMAT_INVALID)
    }

    @Test
    fun `parse 함수로 5글자를 초과하는 태그를 파싱하면 실패한다`() {
        val exception = assertFailsWith<ValidationException> {
            TagGroup.parse("매우긴태그이름, 짧음")
        }
        assert(exception.message == ValidationMessages.TAG_LIMIT_INVALID)
    }

    @Test
    fun `parse 함수로 5개를 초과하는 태그를 파싱하면 실패한다`() {
        val exception = assertFailsWith<ValidationException> {
            TagGroup.parse("하나, 둘, 셋, 넷, 다섯, 여섯")
        }
        assert(exception.message == ValidationMessages.TAG_LIMIT_INVALID)
    }
}
