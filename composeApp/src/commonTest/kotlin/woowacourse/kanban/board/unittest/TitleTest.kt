package woowacourse.kanban.board.unittest

import org.junit.Test
import woowacourse.kanban.board.taskcard.domain.Title
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class TitleTest {

    @Test
    fun `제목에 아무것도 입력되지 않으면 isNotValidTitle이 true를 반환한다`() {
        val title = Title("")

        assertTrue(title.isNotValidTitle())
    }

    @Test
    fun `제목에 공백만 입력되면 isNotValidTitle이 true를 반환한다`() {
        val title = Title("        ")

        assertTrue(title.isNotValidTitle())
    }

    @Test
    fun `제목에 문자가 입력되면 isNotValidTitle이 false를 반환한다`() {
        val title = Title("title")

        assertFalse(title.isNotValidTitle())
    }
}