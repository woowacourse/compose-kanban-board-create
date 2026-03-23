package woowacourse.kanban.board.ui.sample

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.Status
import woowacourse.kanban.board.model.Tag
import woowacourse.kanban.board.model.TaskCard

class TaskCardPreviewData : PreviewParameterProvider<TaskCard> {
    override val values: Sequence<TaskCard> = sequenceOf(
        TaskCard(
            title = "LazyColumn 컴포넌트 구현",
            description = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
            tags = Tag(listOf("컴포넌트", "성능")),
            assignee = Assignee("다이노"),
            status = Status.TODO,
        ),
        TaskCard(
            title = "Side-effect API 학습",
            description = "LaunchedEffect, DisposableEffect 등의 API를 학습하고 적절한 사용 예제를 작성합니다.",
            tags = Tag(listOf("학습", "API")),
            assignee = Assignee("페임스"),
            status = Status.TODO,
        ),
        TaskCard(
            title = "상태 관리 리팩토링",
            description = "복잡한 상태를 효율적으로 관리하기 위한 구조를 설계합니다.",
            tags = Tag(listOf("리팩토링", "상태관리")),
            assignee = Assignee("다이노"),
            status = Status.INPROGRESS,
        ),
        TaskCard(
            title = "리컴포지션 최적화",
            description = "derivedStateOf와 key를 활용하여 불필요한 리컴포지션을 방지합니다.",
            tags = Tag(listOf("최적화", "성능")),
            assignee = Assignee("다이노"),
            status = Status.DONE,
        ),
        TaskCard(
            title = "Mock API 설정",
            description = "JSON 파일 또는 Mock API를 통해 초기 데이터를 로드하는 로직을 구현합니다.",
            tags = Tag(listOf("API", "비동기")),
            assignee = Assignee("페임스"),
            status = Status.DONE,
        ),
        TaskCard(
            title = "Drag & Drop 기능 구현",
            description = "카드를 드래그하여 다른 컬럼으로 이동할 수 있는 기능을 구현합니다.",
            tags = Tag(listOf("기능", "UX")),
            assignee = Assignee("다이노"),
            status = Status.DONE,
        ),
    )
}
