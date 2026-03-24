package woowacourse.kanban.newTaskCreate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import org.jetbrains.compose.resources.painterResource
import woowacourse.kanban.board.CustomColor

@Composable
fun ProfileCard(nickname: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(Res.drawable.profile),
            contentDescription = "프로필 이미지",
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = nickname,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = CustomColor.Gray700,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
@Preview
private fun ProfilePreview(@PreviewParameter(ProfilePreviewParameterProvider::class) nickname: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(Res.drawable.profile),
            contentDescription = "프로필 이미지",
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = nickname,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = CustomColor.Gray700,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

private class ProfilePreviewParameterProvider() : PreviewParameterProvider<String> {
    override val values = sequenceOf<String>(
        "다이노",
        "프로필",
        "닉네임",
        "아주아주매우매우긴닉네임",
    )
}
