package woowacourse.kanban.board.ui.taskCard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import woowacourse.kanban.board.fixture.TaskCardFixture
import kotlin.test.Test
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.Tag

@OptIn(ExperimentalTestApi::class)
class TaskCardSectionTest {
    @Test
    fun `태스크 카드는 제목, 설명, 태그, 담당자를 모두 표시한다`() = runComposeUiTest {
        val task = TaskCardFixture.create()

        setContent {
            TaskCardSection(
                title = task.title,
                description = task.description,
                tags = task.tags,
                assignee = task.assignee,
            )
        }

        onNodeWithText(task.title).assertIsDisplayed()
        onNodeWithText(task.description).assertIsDisplayed()

        onNodeWithText("태그1").assertIsDisplayed()
        onNodeWithText("태그2").assertIsDisplayed()
        onNodeWithText(task.assignee.name).assertIsDisplayed()
        onNodeWithContentDescription("사용자 기본 이미지").assertIsDisplayed()
    }
}
