package woowacourse.kanban.board.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import woowacourse.kanban.board.domain.TaskState
import woowacourse.kanban.board.ui.card.Card
import woowacourse.kanban.board.ui.card.CardState
import woowacourse.kanban.board.ui.cardForm.getTaskStateLabel

@Composable
fun StateColumnLayout(
    taskState: TaskState,
    cards: List<CardState>,
    modifier: Modifier = Modifier,
) {
    val countOfCards = cards.count()

    val headerColor: Color = when (taskState) {
        TaskState.TO_DO -> Color(0xFF155DFC)
        TaskState.IN_PROGRESS -> Color(0xFFE17100)
        TaskState.DONE -> Color(0xFF00A63E)
    }
    val contentColor: Color = when (taskState) {
        TaskState.TO_DO -> Color(0xFFEFF6FF)
        TaskState.IN_PROGRESS -> Color(0xFFFFFBEB)
        TaskState.DONE -> Color(0xFFF0FDF4)
    }
    val outlineColor: Color = when (taskState) {
        TaskState.TO_DO -> Color(0xFFBEDBFF)
        TaskState.IN_PROGRESS -> Color(0xFFFEE685)
        TaskState.DONE -> Color(0xFFB9F8CF)
    }

    Column(
        modifier = modifier.size(width = 320.dp, height = 748.dp).clip(RoundedCornerShape(10.dp)),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(48.dp).background(headerColor).padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = taskState.getTaskStateLabel(),
                color = Color(0xFFFFFFFF),
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                letterSpacing = (-0.31).sp,
                lineHeight = 24.sp,
            )

            Text(
                text = countOfCards.toString(),
                modifier = Modifier.clip(RoundedCornerShape(30.dp)).background(Color.White).padding(horizontal = 10.dp, vertical = 2.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                letterSpacing = (-0.15).sp,
                lineHeight = 20.sp,
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize().background(contentColor).border(width = 1.dp, color = outlineColor),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 17.dp, vertical = 16.dp),
        ) {
            items(cards) { card ->
                Card(card)
            }
        }
    }
}

@Preview(showBackground = true, name = "투두 컬럼레이아웃")
@Composable
private fun ToDoColumnLayoutPreview() {
    val fakeCards = listOf(
        CardState(title = "제목", state = TaskState.TO_DO, managerName = "별터"),
        CardState(title = "제목2", state = TaskState.TO_DO, managerName = "별터2", tags = listOf("태그")),
    )

    StateColumnLayout(
        taskState = TaskState.TO_DO,
        cards = fakeCards,
    )
}

@Preview(showBackground = true, name = "인프로그레스 컬럼레이아웃")
@Composable
private fun InProgressColumnLayoutPreview() {
    val fakeCards = listOf(
        CardState(title = "제목", state = TaskState.TO_DO, managerName = "별터"),
        CardState(title = "제목2", state = TaskState.TO_DO, managerName = "별터2", tags = listOf("태그")),
    )

    StateColumnLayout(
        taskState = TaskState.IN_PROGRESS,
        cards = fakeCards,
    )
}

@Preview(showBackground = true, name = "던 컬럼레이아웃")
@Composable
private fun DoneColumnLayoutPreview() {
    val fakeCards = listOf(
        CardState(title = "제목", state = TaskState.TO_DO, managerName = "별터"),
        CardState(title = "제목2", state = TaskState.TO_DO, managerName = "별터2", tags = listOf("태그")),
    )

    StateColumnLayout(
        taskState = TaskState.DONE,
        cards = fakeCards,
    )
}
