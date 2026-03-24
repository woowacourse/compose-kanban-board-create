package woowacourse.kanban.board.model

import kotlin.test.Test
import kotlin.test.assertFailsWith

class TitleTest {
    @Test
    fun `제목은 빈 텍스트일 수 없다`() {
        assertFailsWith<IllegalArgumentException> {
            Title(" ")
        }
    }

    @Test
    fun `제목은 텍스트로 입력 시, text에 입력된다`() {
        val title = Title("제대로 된 제목입니다.")
        assert(title.text == "제대로 된 제목입니다.")
    }

    @Test
    fun `제목이 비어있으면 제목 필수 예외가 발생한다`() {
        val exception = assertFailsWith<ValidationException> {
            Title("")
        }
        assert(exception.message == ValidationMessages.TITLE_REQUIRED)
    }

    @Test
    fun `제목이 공백만 있으면 제목 필수 예외가 발생한다`() {
        val exception = assertFailsWith<ValidationException> {
            Title("   ")
        }
        assert(exception.message == ValidationMessages.TITLE_REQUIRED)
    }
}
