package woowacourse.kanban.board.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.tag_label_supporting
import kanbanboard.composeapp.generated.resources.unknown_user
import org.jetbrains.compose.resources.stringResource
import woowacourse.kanban.board.domain.model.User
import woowacourse.kanban.board.ui.preview.KanbanPreview
import woowacourse.kanban.board.ui.theme.Gray400
import woowacourse.kanban.board.ui.theme.Gray700

@Composable
fun CardUserProfile(user: User?, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "profile image",
            tint = Gray400,
            modifier = Modifier.requiredSize(size = 33.dp),
        )

        Text(
            text = user?.name ?: stringResource(Res.string.unknown_user),
            fontWeight = FontWeight.W500,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = Gray700,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
@KanbanPreview
fun CardUserProfilePreview() {
    CardUserProfile(User("다이노"))
}
