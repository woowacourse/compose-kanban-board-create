package woowacourse.kanban.board.component.taskcard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.Gray70
import woowacourse.kanban.board.Gray80
import woowacourse.kanban.board.model.TaskCardData
import woowacourse.kanban.board.model.modal.Tags

@Composable
fun TaskCard(
    data: TaskCardData,
    modifier: Modifier = Modifier,
    maxTagCount: Int = Tags.MAX_TAGS,
    maxTagTextLength: Int = Tags.TAG_MAX_TEXT_LENGTH,
) {
    Card(
        modifier = modifier
            .width(286.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(1.dp, Gray70),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = modifier
                .padding(17.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Title(data.title)
            Script(data.script)
            Tags(
                tags = data.tags,
                maxTagCount = maxTagCount,
                maxTagTextLength = maxTagTextLength,
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = Gray80,
            )
            Profile(data.nickname)
        }
    }
}

@Preview
@Composable
private fun TaskCardPreview() {
    TaskCard(
        data = TaskCardData(
            title = "LazyColumn 컴포넌트 구현",
            script = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
            tags = listOf("컴포넌트", "성능"),
            nickname = "다이노",
        ),
    )
}
