package woowacourse.kanban.board.data

import org.junit.Assert.assertThrows
import org.junit.Test
import kotlin.test.assertEquals

class TagsTest {
    @Test
    fun `태그에 5자 이상 또는 5개 이상의 태그가 들어올 경우 IllegalArgumentException을 발생시킨다`() {
        val wrongTag1 = listOf("fivetag", "overfive")
        val wrongTag2 = listOf("tag1", "tag2", "tag3", "tag4", "tag5", "tag6")

        assertThrows(IllegalArgumentException::class.java) {
            Tags(wrongTag1)
        }

        assertThrows(IllegalArgumentException::class.java) {
            Tags(wrongTag2)
        }
    }

    @Test
    fun `정상적인 태그거나 값이 안들어 온 경우 null을 반환한다`() {
        val testString1 = ""
        val testString2 = "Tag1"
        val testString3 = "Tag1, Tag2, Tag3, Tag4, Tag5"

        assertEquals(null, Tags.validateTagsAndWordCount(testString1))
        assertEquals(null, Tags.validateTagsAndWordCount(testString2))
        assertEquals(null, Tags.validateTagsAndWordCount(testString3))
    }
}
