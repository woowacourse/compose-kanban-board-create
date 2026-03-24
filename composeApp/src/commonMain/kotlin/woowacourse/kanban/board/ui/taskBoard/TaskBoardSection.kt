package woowacourse.kanban.board.ui.taskBoard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.launch
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.Description
import woowacourse.kanban.board.model.Tag
import woowacourse.kanban.board.model.TagGroup
import woowacourse.kanban.board.model.TaskCard
import woowacourse.kanban.board.model.TaskStatus
import woowacourse.kanban.board.model.Title
import woowacourse.kanban.board.ui.taskCardForm.TaskCreateSection

@Composable
fun TaskBoardSection(
    tasks: List<TaskCard>,
    modifier: Modifier = Modifier,
) {
    val taskCards = remember(tasks) { tasks.toMutableStateList() }
    val showTaskCreateDialog = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        snackbarHost = { TaskBoardSnackbar(snackbarHostState = snackbarHostState) },
    ) { innerPadding ->
        TaskBoardContentSection(
            tasks = taskCards,
            onCreateTaskClick = { showTaskCreateDialog.value = true },
            modifier = Modifier.padding(innerPadding),
        )
    }

    if (showTaskCreateDialog.value) {
        Dialog(
            onDismissRequest = { showTaskCreateDialog.value = false },
            properties = DialogProperties(usePlatformDefaultWidth = false),
        ) {
            Surface(
                modifier = Modifier.width(860.dp),
            ) {
                TaskCreateSection(
                    onTaskCreate = { createdTask ->
                        taskCards.add(createdTask)
                        showTaskCreateDialog.value = false
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(message = "새로운 태스크가 추가되었습니다.")
                        }
                    },
                    onCancel = { showTaskCreateDialog.value = false },
                )
            }
        }
    }
}

@Composable
private fun TaskBoardContentSection(
    tasks: List<TaskCard>,
    onCreateTaskClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val statuses = listOf(
        TaskStatus.TODO,
        TaskStatus.INPROGRESS,
        TaskStatus.DONE,
    )

    val doneTasks = tasks.filter { it.status == TaskStatus.DONE }

    val doneCount = doneTasks.size
    val totalCount = tasks.size
    val ratio = calculateProgress(doneCount, totalCount)

    Column(
        modifier = modifier
    ) {
        TaskBoardHeader(onCreateTaskClick = onCreateTaskClick)
        TaskBoardProgressSection(
            doneCount = doneCount,
            totalCount = totalCount,
            ratio = ratio,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            statuses.forEach { status ->
                val statusTasks = tasks.filter { it.status == status }
                StatusBoardSection(
                    status = status,
                    taskCardCount = statusTasks.size,
                    tasks = statusTasks,
                    modifier = Modifier.weight(1f),

                    )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 950, heightDp = 1200)
@Composable
private fun TaskBoardSectionPreview() {
    TaskBoardSection(
        tasks = previewTasks,
    )
}

private val previewTasks = listOf(
    TaskCard(
        title = Title("LazyColumn 컴포넌트 구현"),
        status = TaskStatus.TODO,
        assignee = Assignee("다이노"),
        description = Description("세로 스크롤 가능한 리스트 컴포넌트를 만듭니다."),
        tags = TagGroup(listOf(Tag("컴포넌트"), Tag("성능"))),
    ),
    TaskCard(
        title = Title("Side-effect API 학습"),
        status = TaskStatus.TODO,
        assignee = Assignee("페임스"),
        description = Description("LaunchedEffect, DisposableEffect 예제를 학습합니다."),
        tags = TagGroup(listOf(Tag("학습"), Tag("API"))),
    ),
    TaskCard(
        title = Title("상태 관리 리팩토링"),
        status = TaskStatus.INPROGRESS,
        assignee = Assignee("다이노"),
        description = Description("복잡한 상태를 효율적으로 관리합니다."),
        tags = TagGroup(listOf(Tag("리팩토"), Tag("상태"))),
    ),
    TaskCard(
        title = Title("Mock API 설정"),
        status = TaskStatus.DONE,
        assignee = Assignee("페임스"),
        description = Description("소개 데이터를 로드하는 로직을 구현합니다."),
        tags = TagGroup(listOf(Tag("API"), Tag("비동기"))),
    ),
    TaskCard(
        title = Title("Drag & Drop 기능 구현"),
        status = TaskStatus.DONE,
        assignee = Assignee("다이노"),
        description = Description("다른 컬럼으로 이동하는 기능을 구현합니다."),
        tags = TagGroup(listOf(Tag("기능"), Tag("UX"))),
    ),
    TaskCard(
        title = Title("리컴포지션 최적화"),
        status = TaskStatus.DONE,
        assignee = Assignee("페임스"),
        description = Description("불필요한 리컴포지션을 줄입니다."),
        tags = TagGroup(listOf(Tag("최적화"), Tag("성능"))),
    ),
)

