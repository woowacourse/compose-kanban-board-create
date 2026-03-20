package woowacourse.kanban.board.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.kanban.board.domain.TaskState

private val cardUiStates = listOf(
    CardUiState(
        title = "제목",
        description = "설명",
        tags = listOf("태그"),
        state = TaskState.TO_DO,
        managerName = "별터",
    ),
    CardUiState(
        title = "제목",
        description = "설명",
        tags = listOf("태그"),
        state = TaskState.TO_DO,
        managerName = "별터",
    ),
)

@Preview(showBackground = true, name = "투두 컬럼레이아웃")
@Composable
private fun ToDoColumnLayoutPreview() {
    StateColumnLayout(
        taskState = TaskState.TO_DO,
        cardUiStates = cardUiStates
    )
}

@Preview(showBackground = true, name = "인프로그레스 컬럼레이아웃")
@Composable
private fun InProgressColumnLayoutPreview() {
    StateColumnLayout(
        taskState = TaskState.IN_PROGRESS,
        cardUiStates = cardUiStates
    )
}

@Preview(showBackground = true, name = "던 컬럼레이아웃")
@Composable
private fun DoneColumnLayoutPreview() {
    StateColumnLayout(
        taskState = TaskState.DONE,
        cardUiStates = cardUiStates
    )
}
