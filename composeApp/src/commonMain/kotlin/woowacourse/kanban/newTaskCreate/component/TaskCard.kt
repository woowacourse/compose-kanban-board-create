package woowacourse.kanban.newTaskCreate.component

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
        border = BorderStroke(1.dp, CustomColor.Gray200),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(17.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            TaskTitle(title)
            if (script.isNotBlank()) ScriptText(
                script,
                modifier = Modifier.testTag("script_area")
            )
            if (tags.isNotEmpty()) TagList(
                tags,
                tagsModifier = Modifier.fillMaxWidth().testTag("tags_area")
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = CustomColor.Gray100,
            )
            ProfileCard(
                nickname = nickname,
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskCardPreview() {
    TaskCard(
        title = "LazyColumn 컴포넌트 구현",
        script = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
        tags = listOf("컴포넌트", "성능"),
        nickname = "다이노",
    )
}
