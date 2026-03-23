package woowacourse.kanban.board.component.taskcard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Test
import woowacourse.kanban.board.model.Assignee

@OptIn(ExperimentalTestApi::class)
class KanbanCardTagsTest {
    @Test
    fun `빈 태그 리스트 테스트`() = runComposeUiTest {
        val kanbanCardForm = KanbanCardForm(
            title = "LazyColumn 컴포넌트 구현",
            assignee = Assignee("다이노"),
            content = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
        )

        setContent {
            KanbanCard(
                kanbanCardForm = kanbanCardForm,
            )
        }

        onNodeWithContentDescription("칸반 카드 태그 목록").assertDoesNotExist()
    }
}
