package woowacourse.kanban.board.ui.taskcard

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import woowacourse.kanban.board.taskcard.domain.Manager
import woowacourse.kanban.board.taskcard.domain.State
import kotlin.test.Test
import woowacourse.kanban.board.taskcard.domain.TaskCardData
import woowacourse.kanban.board.ui.taskcard.ui.TaskCard

@OptIn(ExperimentalTestApi::class)
class TaskCardTest {

    @Test
    fun `모든 필드가 있는 카드 - 제목, 설명, 태그, 닉네임 출력`() = runComposeUiTest {
        val tags = listOf("컴포넌트", "성능")
        val taskCardData = TaskCardData(
            title = "LazyColumn 컴포넌트 구현",
            description = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
            tags = tags,
            state = State.TODO,
            manager = Manager.DINO,
        )
        setContent {
            TaskCard(data = taskCardData)
        }

        onNodeWithText("LazyColumn 컴포넌트 구현").assertExists()
        onNodeWithText("세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.").assertExists()
        tags.forEach { tag ->
            onNodeWithText(tag).assertExists()
        }
        onNodeWithText("다이노").assertExists()
    }
}
