package woowacourse.kanban.board.ui.taskBoard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskBoardHeader(
    onCreateTaskClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TaskBoardTitle()
        TaskCardCreateButton(onClick = onCreateTaskClick)
    }
}

@Composable
private fun TaskBoardTitle(
    modifier: Modifier = Modifier
) {
    Text(
        text = "Compose Desktop 칸반 보드",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier,
    )
}

@Composable
private fun TaskCardCreateButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier,
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4F39F6),
            contentColor = Color.White,
        ),
    ) {
        Text("+ 새 태스크 생성")
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskBoardHeaderPreview() {
    TaskBoardHeader(
        onCreateTaskClick = {},
    )
}

