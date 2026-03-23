package woowacourse.kanban.board

import androidx.compose.runtime.Composable
import woowacourse.kanban.board.component.screen.KanbanBoardScreen
import woowacourse.kanban.board.data.Nickname
import woowacourse.kanban.board.data.Script
import woowacourse.kanban.board.data.Tags
import woowacourse.kanban.board.data.Task
import woowacourse.kanban.board.data.TaskStatus
import woowacourse.kanban.board.data.Title

@Composable
fun App() {
    KanbanBoardScreen()
}

val tasksExample = listOf<Task>(
    Task(
        Title("LazyColumn 컴포넌트 구현"),
        Script("세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다."),
        Tags(listOf("컴포넌트", "성능")),
        TaskStatus.TO_DO,
        Nickname("다이노"),
    ),
    Task(
        taskTitle = Title("LazyColumn 컴포넌트 구현"),
        tags = Tags(listOf("컴포넌트", "성능")),
        status = TaskStatus.TO_DO,
        nickname = Nickname("다이노"),
    ),
    Task(
        taskTitle = Title("LazyColumn 컴포넌트 구현"),
        taskScript = Script("세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다."),
        status = TaskStatus.IN_PROGRESS,
        nickname = Nickname("다이노"),
    ),
    Task(
        taskTitle = Title("LazyColumn 컴포넌트 구현"),
        status = TaskStatus.DONE,
        nickname = Nickname("다이노"),
    ),
    Task(
        taskTitle = Title("너무 너무 긴 제목은 한 줄 까지만 노출시킵니다."),
        taskScript = Script("너무 너무 너무 긴 설명은 두 줄까지만 노출하고 말 줄임표로 처리합니다. 두 줄 까지만 노출합니다."),
        tags = Tags(listOf("너무너무", "긴 태그", "최대로", "5자까지", "5개제한임")),
        status = TaskStatus.TO_DO,
        nickname = Nickname("다이노"),
    ),
)
