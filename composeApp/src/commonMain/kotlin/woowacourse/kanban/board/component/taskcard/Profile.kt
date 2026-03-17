package woowacourse.kanban.board.component.taskcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import org.jetbrains.compose.resources.painterResource
import woowacourse.kanban.board.Gray20

@Composable
fun Profile(nickname: String?, modifier: Modifier = Modifier) {
    if (!nickname.isNullOrEmpty()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(Res.drawable.profile),
                contentDescription = "프로필 이미지",
                modifier = modifier.size(24.dp),
            )
            Spacer(modifier = modifier.width(8.dp))
            Text(
                text = nickname,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Gray20,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfilePreview() {
    Profile("다이노다이노 너무 긴 이름이면 줄임표 나오는지 확인해보자다이노다이노 너무 긴 이름이면 줄임표 나오는지 확인해보자")
}
