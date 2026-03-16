package woowacourse.kanban.board.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import woowacourse.kanban.TAG_COLOR
import woowacourse.kanban.board.model.Tags

@Composable
fun TagsComponent(
    tags: Tags,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        for (tag in tags.tags) {
            Tag(
                tag,
                modifier = Modifier
                    .background(
                        color = Color(TAG_COLOR),
                        shape = RoundedCornerShape(45.dp),
                    ),
            )
        }
    }
}
