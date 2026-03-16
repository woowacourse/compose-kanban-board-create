package woowacourse.kanban.board.model

import androidx.compose.ui.test.ExperimentalTestApi
import kotlin.test.Test
import org.junit.Assert
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class TagsTest {

    @Test
    fun `태그 정상 케이스`() {
        // given
        val tags =
            listOf(
                "일",
                "이",
                "삼",
                "사",
                "오",
            )

        // when
        val data =
            Tags(tags)

        // then
        assertEquals(
            listOf<String>(
                "일",
                "이",
                "삼",
                "사",
                "오",
            ),
            data.tags,
        )
    }

    @Test
    fun `태그의 개수가 5개 초과면 오류가 발생한다`() {
        // given
        val tags =
            listOf(
                "일",
                "이",
                "삼",
                "사",
                "오",
                "육",
            )
        // when
        // then
        Assert.assertThrows(IllegalArgumentException::class.java) {
            Tags(tags)
        }
    }
}
