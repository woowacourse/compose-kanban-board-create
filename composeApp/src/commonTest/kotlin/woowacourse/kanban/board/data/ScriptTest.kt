package woowacourse.kanban.board.data

import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.Test

class ScriptTest {
    @Test
    fun `설명이 있다면 해당 객체를 생성한다`() {
        val script = "description test"

        assertThatNoException().isThrownBy {
            Script(script)
        }
    }

    @Test
    fun `설명이 비어있어도 해당 객체를 생성한다`() {
        val script = ""

        assertThatNoException().isThrownBy {
            Script(script)
        }
    }
}
