package woowacourse.kanban.board.component.kanbanboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.TaskStatusUIModel
import woowacourse.kanban.board.component.task.TaskCard
import woowacourse.kanban.board.data.Task
import woowacourse.kanban.board.tasksExample

@Composable
fun TaskStatusCardHolder(
    tasks: List<Task>,
    tasksSize: Int,
    tasksStatusUIModel: TaskStatusUIModel,
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(
                color = tasksStatusUIModel.backgroundColor,
            )
            .width(320.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TaskStatusCardHolderHeader(
            taskStatusString = tasksStatusUIModel.text,
            tasksSize = tasksSize,
            modifier = Modifier
                .background(
                    color = tasksStatusUIModel.headerColor,
                )
                .fillMaxWidth()
                .padding(20.dp),
        )
        TaskCardHolder(
            tasks = tasks,
            modifier = Modifier
                .border(
                    color = tasksStatusUIModel.borderColor,
                    width = 1.dp,
                )
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    vertical = 20.dp,
                    horizontal = 10.dp,
                ),
        )
    }
}

@Composable
private fun TaskStatusCardHolderHeader(
    taskStatusString: String,
    tasksSize: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = taskStatusString,
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp,
            color = Color.White,
        )
        Text(
            text = tasksSize.toString(),
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp),
                )
                .padding(
                    vertical = 8.dp,
                    horizontal = 10.dp,
                ),
        )
    }
}

@Composable
private fun TaskCardHolder(
    tasks: List<Task>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(tasks) { task ->
            TaskCard(
                task.taskTitle.titleText,
                task.taskScript.scriptText,
                task.tags.tags,
                task.nickname.nicknameText,
            )
        }
    }
}

@Preview
@Composable
private fun TaskStatusCardHolderPreview() {
    TaskStatusCardHolder(
        tasks = tasksExample,
        tasksSize = tasksExample.size,
        tasksStatusUIModel = TaskStatusUIModel.TO_DO,
    )
}
