package woowacourse.kanban.board.component.taskboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.component.taskcard.KanbanCard
import woowacourse.kanban.board.component.taskcard.KanbanCardForm
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.TaskState

@Composable
fun KanbanCardHolder(
    tasks: List<KanbanCardForm>,
    state: TaskState,
    holderColor: HolderColor,
    modifier: Modifier = Modifier,
) {
    val stateName = when (state) {
        TaskState.TODO -> "To Do"
        TaskState.IN_PROGRESS -> "In Progress"
        TaskState.DONE -> "Done"
    }
    Column(modifier = modifier.width(320.dp))
    {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .width(320.dp)
                .height(48.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                    ),
                )
                .background(color = holderColor.headerContainer)
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = stateName,
                fontSize = 16.sp,
                color = Color.White,
            )

            Text(
                text = tasks.size.toString(),
                modifier = Modifier
                    .height(24.dp)
                    .clip(shape = RoundedCornerShape(16777200.dp))
                    .background(Color.White)
                    .padding(horizontal = 10.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically),
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(color = holderColor.contentContainer)
                .border(1.dp, holderColor.contentBorder)
                .padding(
                    vertical = 17.dp,
                    horizontal = 16.dp,
                ),
        ) {
            items(tasks) { task ->
                KanbanCard(
                    kanbanCardForm = task,
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    heightDp = 1000,
)
@Composable
fun KanbanCardHolderPreview() {
    val tags = listOf(
        "컴포넌트",
        "성능",
    )

    val values = listOf(
        KanbanCardForm(
            title = "LazyColumn 컴포넌트 구현",
            assignee = Assignee(name = "바드"),
            tags = tags,
            content = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
        ),
        KanbanCardForm(
            title = "LazyColumn 컴포넌트 구현",
            assignee = Assignee(name = "바드"),
            tags = tags,
            content = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
        ),
        KanbanCardForm(
            title = "LazyColumn 컴포넌트 구현",
            assignee = Assignee(name = "바드"),
            content = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
        ),
        KanbanCardForm(
            title = "LazyColumn 컴포넌트 구현",
            assignee = Assignee(name = "바드"),
        ),
    )

    KanbanCardHolder(
        tasks = values,
        holderColor = HolderColor(
            headerContainer = Color(0xFF155DFC),
            contentContainer = Color(0xFFEFF6FF),
            contentBorder = Color(0xFFBEDBFF),
        ),
        state = TaskState.TODO,
    )
}
