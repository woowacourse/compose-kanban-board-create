package woowacourse.kanban.board.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.CustomColor
import woowacourse.kanban.board.data.KanbanBoardSampleData
import woowacourse.kanban.newTaskCreate.component.TaskCard
import woowacourse.kanban.newTaskCreate.data.Task
import woowacourse.kanban.newTaskCreate.data.TaskStatus

@Composable
fun ProgressCard(
    title: String,
    headerColor: Color,
    borderColor: Color,
    bodyColor: Color,
    tasks: List<Task>,
    assigneeById: Map<String, String>,
    countTag: String = "progress_card_count",
) {
    // 전체 박스
    Column(
        modifier = Modifier
            .size(320.dp, 748.dp)
            .clip(
                RoundedCornerShape(10.dp),
            )
            .border(width = 1.dp, color = borderColor),
    ) {
        // 헤더 부분
        Row(
            modifier = Modifier
                .size(320.dp, 48.dp)
                .background(headerColor)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Box(
                modifier = Modifier
                    .size(29.dp, 24.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = tasks.size.toString(),
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.testTag(countTag),
                )
            }
        }
        // 몸통 부분
        Column(
            modifier = Modifier
                .size(320.dp, 700.dp)
                .background(bodyColor)
                .padding(top = 16.dp, start = 17.dp, end = 17.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            tasks.forEach { task ->
                val nickname = assigneeById[task.assigneeId] ?: "알 수 없음"
                TaskCard(
                    title = task.taskTitle,
                    script = task.taskScript,
                    tags = task.tags,
                    nickname = nickname,
                )
            }
        }
    }
}

data class ProgressCardPreviewModel(
    val title: String,
    val headerColor: Color,
    val bodyColor: Color,
    val borderColor: Color,
    val tasks: List<Task>,
)

class ProgressCardPreviewProvider : PreviewParameterProvider<ProgressCardPreviewModel> {
    override val values = sequenceOf(
        ProgressCardPreviewModel(
            title = "To Do",
            headerColor = CustomColor.Blue600,
            bodyColor = CustomColor.Blue50,
            borderColor = CustomColor.Blue200,
            tasks = KanbanBoardSampleData.Tasks.filter { it.status == TaskStatus.TO_DO },
        ),
        ProgressCardPreviewModel(
            title = "In Progress",
            headerColor = CustomColor.Orange700,
            bodyColor = CustomColor.Yellow100,
            borderColor = CustomColor.Yellow300,
            tasks = KanbanBoardSampleData.Tasks.filter { it.status == TaskStatus.IN_PROGRESS },
        ),
        ProgressCardPreviewModel(
            title = "Done",
            headerColor = CustomColor.Green700,
            bodyColor = CustomColor.Green50,
            borderColor = CustomColor.Green200,
            tasks = KanbanBoardSampleData.Tasks.filter { it.status == TaskStatus.DONE },
        ),
    )

}

@Preview(showBackground = true)
@Composable
private fun ProgressCardPreview(
    @PreviewParameter(ProgressCardPreviewProvider::class)
    model: ProgressCardPreviewModel,
) {
    ProgressCard(
        title = model.title, // 헤더:TO DO/In Progress/Done
        headerColor = model.headerColor,
        bodyColor = model.bodyColor,
        borderColor = model.borderColor,
        tasks = model.tasks,
        assigneeById = KanbanBoardSampleData.assignees.associate { it.id to it.nickname },
    )
}
