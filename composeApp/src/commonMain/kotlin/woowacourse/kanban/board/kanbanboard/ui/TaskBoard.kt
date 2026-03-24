package woowacourse.kanban.board.kanbanboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.ui.taskcard.ui.TaskCard
import woowacourse.kanban.board.constant.ColorPalette
import woowacourse.kanban.board.taskcard.domain.Manager
import woowacourse.kanban.board.taskcard.domain.State
import woowacourse.kanban.board.taskcard.domain.TaskCardData
import woowacourse.kanban.board.taskcard.domain.value

@Composable
fun TaskBoard(taskState: State, taskCards: List<TaskCardData>, modifier: Modifier = Modifier) {

    val headerColor = when(taskState){
        State.TODO -> ColorPalette.TODO_HEADER
        State.IN_PROGRESS -> ColorPalette.IN_PROGRESS_HEADER
        State.DONE -> ColorPalette.DONE_HEADER
    }

    val bodyColor = when(taskState){
        State.TODO -> ColorPalette.TODO_BODY
        State.IN_PROGRESS -> ColorPalette.IN_PROGRESS_BODY
        State.DONE -> ColorPalette.DONE_BODY
    }

    Column(
        modifier = modifier
            .width(320.dp)
            .fillMaxHeight()
    ) {
        Row(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                .background(color = headerColor)
                .padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Text(
                text = taskState.value(),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
            )
            Text(
                text = taskCards.size.toString(),
                modifier = Modifier
                    .width(30.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color(0xFFFFFFFF)),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                .background(bodyColor)
                .padding(16.dp)
                .testTag("태스크 카드 칼럼"),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            items(taskCards.size) {
                TaskCard(taskCards[it])
            }
        }

    }
}

@Preview(showBackground = false)
@Composable
fun TodoTaskBoardTest(){
    TaskBoard(
        State.TODO,
        listOf(
            TaskCardData(title = "title", state = State.TODO, manager = Manager.DINO),
            TaskCardData(title = "title", state = State.TODO, manager = Manager.DINO)
        )
    )
}

@Preview(showBackground = false)
@Composable
fun InProgressTaskBoardTest(){
    TaskBoard(
        State.IN_PROGRESS,
        listOf(
            TaskCardData(title = "title", state = State.TODO, manager = Manager.DINO),
            TaskCardData(title = "title", state = State.TODO, manager = Manager.DINO)
        )
    )
}

@Preview(showBackground = false)
@Composable
fun DoneTaskBoardTest(){
    TaskBoard(
        State.DONE,
        listOf(
            TaskCardData(title = "title", state = State.TODO, manager = Manager.DINO),
            TaskCardData(title = "title", state = State.TODO, manager = Manager.DINO)
        )
    )
}
