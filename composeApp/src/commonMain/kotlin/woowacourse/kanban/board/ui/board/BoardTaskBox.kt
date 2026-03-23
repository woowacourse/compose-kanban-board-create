package woowacourse.kanban.board.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
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
import woowacourse.kanban.board.domain.model.Card
import woowacourse.kanban.board.domain.model.Status
import woowacourse.kanban.board.domain.model.User
import woowacourse.kanban.board.ui.card.KanbanCard
import woowacourse.kanban.board.ui.component.toDisplayText
import woowacourse.kanban.board.ui.theme.Blue

@Composable
fun BoardTaskBox(
    boardTaskStatus: Status,
    cards: List<Card>,
    boardTaskCount: Int,
    headerColor: Color,
    mainColor: Color,
    borderColor: Color,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp))
            .background(mainColor)
            .border(color = borderColor, width = 1.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        BoardTaskBox_Header(
            status = boardTaskStatus,
            count = boardTaskCount,
            backgroundColor = headerColor,
        )

        if (cards.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 17.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                itemsIndexed(
                    items = cards,
                    key = { index, card ->
                        "${boardTaskStatus.name}_${card.title}_${card.user?.name}_$index"
                    },
                    contentType = { _, _ -> "kanban_card" },
                ) { _, card ->
                    KanbanCard(
                        card = card,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@Composable
fun BoardTaskBox_Header(
    status: Status,
    count: Int,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = status.toDisplayText(),
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 20.sp,
        )

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.White)
                .padding(horizontal = 10.dp, vertical = 2.dp),
        ) {
            Text(
                text = count.toString(),
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 20.sp,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun BoardTaskBox_HeaderPreview() {
    BoardTaskBox_Header(
        status = Status.TODO,
        count = 10,
        backgroundColor = Blue,
    )
}

@Composable
@Preview(showBackground = true)
private fun BoardTaskBoxPreview() {
    BoardTaskBox(
        boardTaskStatus = Status.TODO,
        cards = listOf(
            Card(
                title = "UI 구현",
                user = User("디노"),
            ),
            Card(
                title = "UI 구현",
                user = User("디노"),
            ),
        ),
        boardTaskCount = 10,
        headerColor = Color(0xff155DFC),
        mainColor = Color(0xffEFF6FF),
        borderColor = Color(0xffBEDBFF),
    )
}
