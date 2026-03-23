package woowacourse.kanban.board.component.task

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.CustomColor

@Composable
fun TaskCard(
    title: String,
    script: String,
    tags: List<String>,
    nickname: String,
) {
    Card(
        modifier = Modifier
            .width(286.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(1.dp, CustomColor.CARD_BORDER.color),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(17.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            TaskTitle(title)
            if (script.isNotBlank()) TaskScript(script, modifier = Modifier.testTag("script_area"))
            if (tags.isNotEmpty()) Chips(tags, modifier = Modifier.fillMaxWidth().testTag("tags_area"))
            HorizontalDivider(
                thickness = 1.dp,
                color = CustomColor.DIVIDER.color,
            )
            Profile(nickname, modifier = Modifier)
        }
    }
}

@Preview
@Composable
private fun TaskCardPreview() {
    TaskCard(
        title = "title1",
        script = "",
        tags = emptyList(),
        nickname = "사무엘",
    )
}
