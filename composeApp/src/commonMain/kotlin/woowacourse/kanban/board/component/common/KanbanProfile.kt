package woowacourse.kanban.board.component.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun KanbanAssigneeProfile(
    crewName: String,
    modifier: Modifier = Modifier,
    crewImage: DrawableResource? = null,
) {
    val imageModifier = Modifier
        .size(24.dp)
        .clip(CircleShape)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        if (crewImage == null) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "기본 이미지",
                tint = Color.Gray,
                modifier = imageModifier,
            )
        } else {
            Image(
                painter = painterResource(resource = crewImage),
                contentDescription = "사용자 이미지",
                modifier = imageModifier,
            )
        }

        Text(
            text = crewName,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun KanbanAssigneeProfilePreview() {
    KanbanAssigneeProfile(crewName = "바드")
}
