package woowacourse.kanban.board.unittest

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import woowacourse.kanban.board.testconstan.TestText
import woowacourse.kanban.board.model.Tags

class TagsTest {
    @Test
    fun `태그의 총 개수가 5개 이상이면 isNotValidTags가 true를 반환한다`() {
        val tags = Tags(TestText.TOO_MANY_TAGS)

        assertTrue(tags.isNotValidTags())
    }

    @Test
    fun `글자 수가 5자 이상인 태그가 있으면 isNotValidTags가 true를 반환한다`() {
        val tags = Tags(TestText.TOO_LONG_TAG)

        assertTrue(tags.isNotValidTags())
    }

    @Test
    fun `입력된 태그가 없으면 isNotValidTags가 false를 반환한다`() {
        val tags = Tags(TestText.BLANK_TEXT)

        assertFalse(tags.isNotValidTags())
    }

    @Test
    fun `입력된 태그 중 5자 이상인 경우가 없고 총 태그의 개수가 5개 이하면 isNotValidTags가 false를 반환한다`() {
        val tags = Tags(TestText.TEST_TAGS)

        assertFalse(tags.isNotValidTags())
    }
}