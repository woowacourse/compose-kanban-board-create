package woowacourse.kanban.board.component.createtaskcard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.Blue50
import woowacourse.kanban.board.Blue80
import woowacourse.kanban.board.Gray20
import woowacourse.kanban.board.Gray70

@Composable
fun StateButton(
    option: String,
    selectedState: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(
                1.dp,
                color = if (selectedState == option) Blue50 else Gray70,
                shape = RoundedCornerShape(10.dp),
            )
            .background(color = if (selectedState == option) Blue80 else Gray70)
            .clickable { onClick(option) }
            .padding(horizontal = 50.dp, vertical = 14.dp),

        ) {
        Text(
            text = option,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 16.sp,
            color = if (selectedState == option) Blue50 else Gray20,
            fontWeight = FontWeight.Normal,
        )
    }
}