package woowacourse.kanban.board.unittest

import org.junit.Assert
import woowacourse.kanban.board.model.TaskCardData
import kotlin.test.Test

class TaskCardDataTest {

    @Test
    fun `TaskCardData의 모든 값이 빈 값 또는 null 이 아니면 예외가 발생하지 않는다`() {
        TaskCardData(
            title = "컴포넌트 구현",
            script = "설명설명",
            tags = listOf("컴포넌트", "성"),
            nickname = "다이노",
        )
    }

    @Test
    fun `TaskCardData의 script만 빈 값이 들어오면 예외가 발생하지 않는다`() {
        TaskCardData(
            title = "컴포넌트 구현",
            script = "",
            tags = listOf("컴포넌트", "성"),
            nickname = "다이노",
        )
    }

    @Test
    fun `TaskCardData의 tags만 빈 값이 들어오면 예외가 발생하지 않는다`() {
        TaskCardData(
            title = "컴포넌트 구현",
            script = "스크립트",
            tags = emptyList(),
            nickname = "다이노",
        )
    }

    @Test
    fun `TaskCardData의 title이 빈 값이면 예외가 발생한다`() {
        Assert.assertThrows(IllegalArgumentException::class.java) {
            TaskCardData(
                title = "",
                script = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
                tags = listOf("컴포넌트", "성"),
                nickname = "다이노",
            )
        }
    }

    @Test
    fun `TaskCardData의 title이 null이면 예외가 발생한다`() {
        Assert.assertThrows(IllegalArgumentException::class.java) {
            TaskCardData(
                title = null,
                script = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
                tags = listOf("컴포넌트", "성"),
                nickname = "다이노",
            )
        }
    }

    @Test
    fun `TaskCardData의 script가 null이면 예외가 발생한다`() {
        Assert.assertThrows(IllegalArgumentException::class.java) {
            TaskCardData(
                title = "컴포넌트 구현",
                script = null,
                tags = listOf("컴포넌트", "성"),
                nickname = "다이노",
            )
        }
    }

    @Test
    fun `TaskCardData의 tags가 null이면 예외가 발생한다`() {
        Assert.assertThrows(IllegalArgumentException::class.java) {
            TaskCardData(
                title = "컴포넌트 구현",
                script = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
                tags = null,
                nickname = "다이노",
            )
        }
    }

    @Test
    fun `TaskCardData의 nickname이 null이면 예외가 발생한다`() {
        Assert.assertThrows(IllegalArgumentException::class.java) {
            TaskCardData(
                title = "컴포넌트 구현",
                script = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
                tags = listOf("컴포넌트", "성"),
                nickname = null,
            )
        }
    }

    @Test
    fun `TaskCardData의 nickname이 빈 값이면 예외가 발생한다`() {
        Assert.assertThrows(IllegalArgumentException::class.java) {
            TaskCardData(
                title = "컴포넌트 구현",
                script = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
                tags = listOf("컴포넌트", "성"),
                nickname = "",
            )
        }
    }
}