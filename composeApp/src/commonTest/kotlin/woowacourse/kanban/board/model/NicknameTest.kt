package woowacourse.kanban.board.model

import androidx.compose.ui.test.ExperimentalTestApi
import kotlin.test.assertEquals
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class NicknameTest {

    @Test
    fun `닉네임 정상 케이스`() {
        // given
        val nickname =
            "아오"

        // when
        val data =
            Nickname(nickname)

        // then
        assertEquals(
            "아오",
            data.nickname,
        )
    }

    @Test
    fun `닉네임이 공백이라면 오류가 발생함`() {
        // given
        val nickname =
            ""
        // when
        // then
        Assert.assertThrows(IllegalArgumentException::class.java) {
            Nickname(nickname)
        }
    }

    @Test
    fun `닉네임에 빈 공간이 있다면 오류가 발생함`() {
        // given
        val nickname =
            " "
        // when
        // then
        Assert.assertThrows(IllegalArgumentException::class.java) {
            Nickname(nickname)
        }
    }
}
