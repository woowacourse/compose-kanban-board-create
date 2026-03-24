package woowacourse.kanban.board

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.kanban.board.component.KanbanBoard
import woowacourse.kanban.board.component.TaskBackground
import woowacourse.kanban.board.data.KanbanBoardSampleData
import woowacourse.kanban.newTaskCreate.data.Task
import woowacourse.kanban.ui.TaskUIMapper

@Composable
@Preview(showBackground = true)
fun App() {
    KanbanBoard(
        tasks = KanbanBoardSampleData.Tasks
    )
}

@Composable
fun MainScreen() {
    val assigneeById = KanbanBoardSampleData.assignees.associate { it.id to it.nickname }
    val tasks = listOf<Task>(
        Task(
            "LazyColumn 컴포넌트 구현",
            "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
            listOf("컴포넌트", "성능"),
            "dino",
        ),
        Task(
            taskTitle = "LazyColumn 컴포넌트 구현",
            tags = listOf("컴포넌트", "성능"),
            assigneeId = "dino",
        ),
        Task(
            taskTitle = "LazyColumn 컴포넌트 구현",
            taskScript = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
            assigneeId = "dino",
        ),
        Task(
            taskTitle = "LazyColumn 컴포넌트 구현",
            assigneeId = "dino",
        ),
        Task(
            taskTitle = "너무 너무 긴 제목은 한 줄 까지만 노출시킵니다.",
            taskScript = "너무 너무 너무 긴 설명은 두 줄까지만 노출하고 말 줄임표로 처리합니다. 두 줄 까지만 노출합니다.",
            tags = listOf("너무너무", "긴 태그", "최대로", "5자까지", "5개제한임"),
            assigneeId = "dino",
        ),
    )

    // Task UI를 생성하는 로직을 Background에 람다로 전달했지만, 더 좋은 방법은 없을까? 람다까지 해야할까?
    TaskBackground { TaskUIMapper().createTaskUI(tasks, assigneeById) }
}

@Composable
fun CheckerScreen() {
    var checked by remember { mutableStateOf(true) }

    CheckerView(checked = checked) {
        checked = !checked
    }
}

@Composable
fun CheckerView(checked: Boolean, check: () -> Unit) {
    Column {
        Checkbox(
            checked = checked,
            onCheckedChange = { check() },
        )
        if (checked) Text(text = "체크됨!")
    }
}
