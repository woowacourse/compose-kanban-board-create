package woowacourse.kanban.board.ui.taskCard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.Description
import woowacourse.kanban.board.model.Tag
import woowacourse.kanban.board.model.TagGroup
import woowacourse.kanban.board.model.TaskCard
import woowacourse.kanban.board.model.TaskStatus
import woowacourse.kanban.board.model.Title

@OptIn(ExperimentalTestApi::class)
class TaskCardSectionTest {

    @Test
    fun `태스크 카드는 제목을 표시한다`() = runComposeUiTest {
        val taskCard = TaskCard(
            title = Title("제목입니다"),
            status = TaskStatus.TODO,
            assignee = Assignee("다이노"),
            description = Description("설명입니다"),
            tags = TagGroup(emptyList()),
        )

        setContent { TaskCardSection(taskCard = taskCard) }

        onNodeWithText("제목입니다").assertIsDisplayed()
    }

    @Test
    fun `태스크 카드는 담당자를 표시한다`() = runComposeUiTest {
        val taskCard = TaskCard(
            title = Title("제목입니다"),
            status = TaskStatus.TODO,
            assignee = Assignee("다이노"),
            description = Description("설명입니다"),
            tags = TagGroup(emptyList()),
        )

        setContent { TaskCardSection(taskCard = taskCard) }

        onNodeWithText("다이노").assertIsDisplayed()
        onNodeWithContentDescription("사용자 기본 이미지").assertIsDisplayed()
    }

    @Test
    fun `태스크 카드는 비어있지 않은 설명을 표시한다`() = runComposeUiTest {
        val taskCard = TaskCard(
            title = Title("제목입니다"),
            status = TaskStatus.TODO,
            assignee = Assignee("다이노"),
            description = Description("설명입니다"),
            tags = TagGroup(emptyList()),
        )

        setContent { TaskCardSection(taskCard = taskCard) }

        onNodeWithText("설명입니다").assertIsDisplayed()
    }

    @Test
    fun `태스크 카드는 비어있는 설명을 숨긴다`() = runComposeUiTest {
        val taskCard = TaskCard(
            title = Title("제목입니다"),
            status = TaskStatus.TODO,
            assignee = Assignee("다이노"),
            description = Description(" "),
            tags = TagGroup(emptyList()),
        )

        setContent { TaskCardSection(taskCard = taskCard) }

        onNodeWithText("제목입니다").assertIsDisplayed()
        onNodeWithText("다이노").assertIsDisplayed()
        onAllNodesWithText(" ").assertCountEquals(0)
    }

    @Test
    fun `태스크 카드는 태그를 표시한다`() = runComposeUiTest {
        val taskCard = TaskCard(
            title = Title("제목입니다"),
            status = TaskStatus.TODO,
            assignee = Assignee("다이노"),
            description = Description("설명입니다"),
            tags = TagGroup(listOf(Tag("태그1"), Tag("태그2"))),
        )

        setContent { TaskCardSection(taskCard = taskCard) }

        onNodeWithText("태그1").assertIsDisplayed()
        onNodeWithText("태그2").assertIsDisplayed()
    }

    @Test
    fun `태스크 카드는 제목, 설명, 태그, 담당자를 모두 표시한다`() = runComposeUiTest {
        val taskCard = TaskCard(
            title = Title("제목입니다"),
            status = TaskStatus.TODO,
            assignee = Assignee("다이노"),
            description = Description("설명입니다"),
            tags = TagGroup(listOf(Tag("태그1"), Tag("태그2"))),
        )

        setContent { TaskCardSection(taskCard = taskCard) }

        onNodeWithText("제목입니다").assertIsDisplayed()
        onNodeWithText("설명입니다").assertIsDisplayed()
        onNodeWithText("태그1").assertIsDisplayed()
        onNodeWithText("태그2").assertIsDisplayed()
        onNodeWithText("다이노").assertIsDisplayed()
        onNodeWithContentDescription("사용자 기본 이미지").assertIsDisplayed()
    }
}
