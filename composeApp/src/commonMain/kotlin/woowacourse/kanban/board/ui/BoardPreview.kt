package woowacourse.kanban.board.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.kanban.board.domain.TaskState

@Preview(showBackground = true, name = "투두 컬럼레이아웃")
@Composable
fun ToDoColumnLayoutPreview() {
    StateColumnLayout(
        taskState = TaskState.TO_DO
    )
}

@Preview(showBackground = true, name = "인프로그레스 컬럼레이아웃")
@Composable
fun InProgressColumnLayoutPreview() {
    StateColumnLayout(
        taskState = TaskState.IN_PROGRESS
    )
}

@Preview(showBackground = true, name = "던 컬럼레이아웃")
@Composable
fun DoneColumnLayoutPreview() {
    StateColumnLayout(
        taskState = TaskState.DONE
    )
}
