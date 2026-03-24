package woowacourse.kanban.board.unit.newTaskCreate

import org.junit.Test
import woowacourse.kanban.newTaskCreate.component.NewTaskFormError
import woowacourse.kanban.newTaskCreate.component.validateNewTaskForm
import woowacourse.kanban.newTaskCreate.component.validateTags
import woowacourse.kanban.newTaskCreate.component.validateTitle
import woowacourse.kanban.newTaskCreate.data.NewTaskFormData
import kotlin.test.assertEquals

class ValidatorTest {
    @Test
    fun `제목이 null 또는 공백인 경우 TITLE_EMPTY를 반환한다`() {
        val title1 = ""
        val title2 = "  "
        val title3 = null

        val expected = NewTaskFormError.TITLE_EMPTY

        assertEquals(expected, validateTitle(title1))
        assertEquals(expected, validateTitle(title2))
        assertEquals(expected, validateTitle(title3))
    }

    @Test
    fun `태그의 개수가 5개가 넘어가는 경우 TAG_LIMIT_EXCEEDED를 반환한다`() {
        val tags = "조디악,사무엘,호이,앨리,아키,허닛"

        val expected = NewTaskFormError.TAG_LIMIT_EXCEEDED

        assertEquals(expected, validateTags(tags))
    }

    @Test
    fun `비어있는 태그가 포함되면 TAG_FORMAT_INVALID를 반환한다`() {
        val tags = "버그,,긴급"

        assertEquals(
            NewTaskFormError.TAG_FORMAT_INVALID,
            validateTags(tags)
        )
    }

    @Test
    fun `폼 전체 검증 시 에러 목록을 순서대로 반환한다`() {
        val form = NewTaskFormData(
            title = "",
            description = "",
            tags = "조디악,사무엘,호이,앨리,아키,허닛",
            selectedStatusIndex = 0,
            selectedProfileIndex = 0,
        )

        val result = validateNewTaskForm(form)

        assertEquals(
            listOf(
                NewTaskFormError.TITLE_EMPTY,
                NewTaskFormError.TAG_LIMIT_EXCEEDED,
            ),
            result,
        )
    }
}
