package woowacourse.kanban.board.data

import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.Assert.assertThrows
import org.junit.Test

class NicknameTest {
    @Test
    fun `닉네임이 빈 문자열일경우 IllegalArgumentException을 발생시킨다`() {
        val nickname = ""
        val nickname2 = "  "
        val nickname3 = "\n"
        val nickname4 = "\n\t"

        assertThrows(IllegalArgumentException::class.java) { Nickname(nickname) }
        assertThrows(IllegalArgumentException::class.java) { Nickname(nickname2) }
        assertThrows(IllegalArgumentException::class.java) { Nickname(nickname3) }
        assertThrows(IllegalArgumentException::class.java) { Nickname(nickname4) }
    }

    @Test
    fun `닉네임이 있는 경우 정상적으로 생성된다`() {
        val nickname = "nickname"

        assertThatNoException().isThrownBy(
            { Nickname(nickname) },
        )
    }
}
