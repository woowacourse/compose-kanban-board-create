package woowacourse.kanban.board.ui.taskBoard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
import woowacourse.kanban.board.model.TaskCard
import woowacourse.kanban.board.model.TaskStatus
import woowacourse.kanban.board.model.statusToBorderColor
import woowacourse.kanban.board.model.statusToContentColor
import woowacourse.kanban.board.model.statusToHeaderColor
import woowacourse.kanban.board.ui.taskCard.TaskCardSection

@Composable
fun StatusBoardSection(
    status: TaskStatus,
    taskCardCount: Int,
    tasks: List<TaskCard>,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, statusToBorderColor(status), RoundedCornerShape(12.dp))
            .background(statusToContentColor(status))
            .heightIn(min = 520.dp)
    ){
        StatusBoardHeader(status = status, taskCardCount = taskCardCount)
        StatusBoardContent(
            status = status,
            tasks = tasks,
            modifier = Modifier.weight(1f, fill = true),
        )
    }
}

@Composable
private fun StatusBoardHeader(
    status: TaskStatus,
    taskCardCount: Int
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .background(color = statusToHeaderColor(status))
            .padding(start = 16.dp, top = 12.dp, bottom = 12.dp, end = 16.dp),
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,

        ) {
            Text(
                text = status.text,
                fontSize = 16.sp,
                color = Color(0xFFFFFFFF),
            )
            Box(
                modifier = Modifier
                    .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ){
                Text(
                    text = taskCardCount.toString(),
                    fontSize = 14   .sp,
                    color = Color(0xFF000000),
                    modifier = Modifier,
                )
            }
        }
    }
}

@Composable
private fun StatusBoardContent(
    status: TaskStatus,
    tasks: List<TaskCard>,
    modifier: Modifier = Modifier,
){
    LazyColumn(
        modifier = modifier
            .background(color = statusToContentColor(status))
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ){
        items(tasks) { task ->
            TaskCardSection(taskCard = task)
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun StatusBoardSectionPreview(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        StatusBoardSection(
            status = TaskStatus.TODO,
            taskCardCount = 2,
            tasks = emptyList(),
        )
        StatusBoardSection(
            status = TaskStatus.INPROGRESS,
            taskCardCount = 1,
            tasks = emptyList(),
        )
        StatusBoardSection(
            status = TaskStatus.DONE,
            taskCardCount = 3,
            tasks = emptyList(),
        )
    }
}
