package woowacourse.kanban.board.data

import woowacourse.kanban.newTaskCreate.data.Assignee
import woowacourse.kanban.newTaskCreate.data.Task
import woowacourse.kanban.newTaskCreate.data.TaskStatus

object KanbanBoardSampleData {
    val assignees = listOf(
        Assignee(id="dino", nickname = "다이노"),
        Assignee(id="fames", nickname = "페임스")
    )
    // 할 일
    val Tasks = listOf(
        Task(
            taskTitle = "LazyColumn 컴포넌트 구현",
            taskScript = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
            tags = listOf("컴포넌트", "성능"),
            assigneeId = "dino",
            status = TaskStatus.TO_DO,
        ),
        Task(
            taskTitle = "Side-effect API 학습",
            taskScript = "LaunchedEffect, DisposableEffect 등의 API를 학습하고 적절한 사용 예제를 작성합니다.",
            tags = listOf("학습", "API"),
            assigneeId = "fames",
            status = TaskStatus.TO_DO,
        ),
        Task(
            taskTitle = "상태 관리 리팩토링",
            taskScript = "복잡한 상태를 효율적으로 관리하기 위한 구조를 설계합니다.",
            tags = listOf("리팩토링", "상태관리"),
            assigneeId = "dino",
            status = TaskStatus.IN_PROGRESS,
        ),
        Task(
            taskTitle = "리컴포지션 최적화",
            taskScript = "derivedStateOf와 key를 활용하여 불필요한 리컴포지션을 방지합니다.",
            tags = listOf("최적화", "성능"),
            assigneeId = "fames",
            status = TaskStatus.DONE,
        ),
        Task(
            taskTitle = "Mock API 설정",
            taskScript = "JSON 파일 또는 Mock API를 통해 초기 데이터를 로드하는 로직을 구현합니다.",
            tags = listOf("API", "비동기"),
            assigneeId = "fames",
            status = TaskStatus.DONE,
        ),
        Task(
            taskTitle = "Drag & Drop 기능 구현",
            taskScript = "카드를 드래그하여 다른 컬럼으로 이동할 수 있는 기능을 구현합니다.",
            tags = listOf("기능", "UX"),
            assigneeId = "dino",
            status = TaskStatus.DONE,
        ),
    )
    val totalCount: Int
        get() = Tasks.size

    val doneCount: Int
        get() = Tasks.count { it.status == TaskStatus.DONE }
}
