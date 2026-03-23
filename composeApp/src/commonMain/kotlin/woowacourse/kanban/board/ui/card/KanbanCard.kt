package woowacourse.kanban.board.ui.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.domain.model.Card
import woowacourse.kanban.board.ui.theme.Gray100
import woowacourse.kanban.board.ui.theme.Gray200

@Composable
fun KanbanCard(card: Card, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(Color.White)
            .border(width = 1.dp, shape = RoundedCornerShape(10.dp), color = Gray200)
            .padding(17.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        CardTitle(card.title)
        card.content?.let { content ->
            CardContent(content)
        }
        if (card.tags.isNotEmpty()) CardTags(card.tags)
        Box {
            HorizontalDivider(color = Gray100, thickness = 1.dp)
            CardUserProfile(card.user, Modifier.padding(10.dp))
        }
    }
}
