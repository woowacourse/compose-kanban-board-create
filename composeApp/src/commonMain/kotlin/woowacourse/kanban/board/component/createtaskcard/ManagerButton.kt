package woowacourse.kanban.board.component.createtaskcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import org.jetbrains.compose.resources.painterResource
import woowacourse.kanban.board.constant.ColorPalette

@Composable
fun ManagerButton(
    option: String,
    selectedOption: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(200.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = if (selectedOption == option) ColorPalette.Blue50 else Color.Transparent,
                shape = RoundedCornerShape(10.dp))
            .background(color = if(selectedOption == option) ColorPalette.Blue80 else ColorPalette.Gray70)
            .clickable { onClick(option) }
            .padding(horizontal = 16.dp, vertical = 20.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
        ) {
            Image(
                painter = painterResource(Res.drawable.profile),
                contentDescription = "프로필 이미지",
                modifier = modifier.size(24.dp),
            )
            Spacer(modifier = modifier.width(12.dp))
            Text(
                text = option,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                color = Color(0xFF364153),
                fontWeight = FontWeight.Normal,
            )
        }
    }
}
