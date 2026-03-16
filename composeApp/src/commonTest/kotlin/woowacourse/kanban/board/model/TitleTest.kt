package woowacourse.kanban.board.model

import androidx.compose.ui.test.ExperimentalTestApi
import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class TitleTest {

    @Test
    fun `제목 정상 케이스`() {
        // given
        val title =
            "제목"
        // when
        val data =
            Title(title)
        // then
        assertEquals(
            "제목",
            data.content,
        )
    }

    @Test
    fun `제목이 비어있는 경우 오류가 발생함`() {
        // given
        val title =
            " "
        // when
        // then
        Assert.assertThrows(IllegalArgumentException::class.java) {
            Title(title)
        }
    }

    @Test
    fun `제목이 공백인 경우 오류가 발생함`() {
        // given
        val title =
            " "
        // when
        // then
        Assert.assertThrows(IllegalArgumentException::class.java) {
            Title(title)
        }
    }
}
