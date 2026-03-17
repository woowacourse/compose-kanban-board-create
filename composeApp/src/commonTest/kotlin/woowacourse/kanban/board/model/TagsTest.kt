package woowacourse.kanban.board.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import woowacourse.kanban.board.model.modal.Tags
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TagsTest {

    @Test
    fun `Tags의 value에 빈 값이 들어오면 빈 값을 value로 가진 Tags가 생성된다`() {
        val tags = Tags(value = "")
        assertThat(tags.value).isEqualTo("")
    }

    @Test
    fun `Tags의 value에 문자가 포함된 값이 입력되면 입력된 값을 value로 가진 Tags가 생성된다`() {
        val tags = Tags(value = "태그1,태그2")
        assertThat(tags.value).isEqualTo("태그1,태그2")
    }

    @Test
    fun `Tags의 value에 콤마가 연속된 값이 입력되면 예외가 발생한다`() {
        assertThatThrownBy {
            Tags(value = "태그1,태그2,,태그3")
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `Tags의 value에 공백으로만 이루어진 값이 포함되어 입력되면 예외가 발생한다`() {
        assertThatThrownBy {
            Tags(value = "태그1,태그2,   ,태그3")
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `Tags의 value에 6개 이상의 값이 입력되면 예외가 발생한다`() {
        assertThatThrownBy {
            Tags(value = "태그1,태그2,태그4,태그3,태그5,태그6")
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `isValidTags에 빈 값이 입력되면 true를 반환한다`() {
        assertTrue { Tags.isTagsValid("") }
    }

    @Test
    fun `isValidTags에 최대 태그수 이하의 태그가 입력되면 true를 반환한다`() {
        assertTrue { Tags.isTagsValid("태그1,태그2,태그3") }
    }

    @Test
    fun `isValidTags에 최대 태그수가 1개 이상일 때, 1개 태그만 입력되면 true를 반환한다`() {
        assertTrue { Tags.isTagsValid("태그하나") }
    }

    @Test
    fun `isValidTags에 공백으로만 값이 입력되면 false를 반환한다`() {
        assertFalse { Tags.isTagsValid("       ") }
    }

    @Test
    fun `isValidTags에 최대 태그 개수를 초과하는 태그가 입력되면 false를 반환한다`() {
        assertFalse { Tags.isTagsValid("태그1,태그2,태그3,태그4,태그5,태그6") }
    }

    @Test
    fun `isValidTags에 최대 글자수를 초과하는 태그가 입력되면 false를 반환한다`() {
        assertFalse { Tags.isTagsValid("태그1,태그2,이건몇글자이지?여덟글자?아닌데?") }
    }
}