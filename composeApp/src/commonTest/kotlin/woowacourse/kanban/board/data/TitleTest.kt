package woowacourse.kanban.board.data

import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.Assert.assertThrows
import kotlin.test.Test

class TitleTest {
    @Test
    fun `제목이 공백이라면 IllegalArgumentException을 발생시킨다`() {
        val title = ""
        val title2 = "  "
        val title3 = "\n\t"

        assertThrows(IllegalArgumentException::class.java) {
            Title(title)
        }

        assertThrows(IllegalArgumentException::class.java) {
            Title(title2)
        }

        assertThrows(IllegalArgumentException::class.java) {
            Title(title3)
        }
    }

    @Test
    fun `제목이 있다면 정상적으로 생성한다`() {
        val title = "title1"
        assertThatNoException().isThrownBy(
            {
                Title(title)
            },
        )
    }
}
