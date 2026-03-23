package woowacourse.kanban.board.ui.kanbanBoard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.model.Status
import woowacourse.kanban.board.model.TaskCard
import woowacourse.kanban.board.ui.taskCard.TaskCardSection
import woowacourse.kanban.board.util.ColorPalette
import woowacourse.kanban.board.util.Text

@Composable
fun KanbanColumnSection(status: Status, tasks: List<TaskCard>) {
    val (headerColor, bodyColor, borderColor) = selectColor(status)
    val shape = RoundedCornerShape(12.dp)

    Column(
        modifier = Modifier
            .width(300.dp)
            .fillMaxHeight()
            .padding(8.dp)
            .clip(shape)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = shape,
            ),
    ) {
        KanbanColumnHeaderSection(
            headerColor = headerColor,
            status = status,
            tasks = tasks,
        )
        KanbanColumnBodySection(
            bodyColor = bodyColor,
            tasks = tasks,
        )
    }
}
@Composable
fun KanbanColumnHeaderSection(headerColor: Color, status: Status, tasks: List<TaskCard>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(headerColor)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = Text.statusLabel(status),
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = tasks.size.toString(),
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.White)
                .padding(horizontal = 12.dp, vertical = 1.dp),
        )
    }
}

@Composable
fun KanbanColumnBodySection(bodyColor: Color, tasks: List<TaskCard>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(bodyColor)
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        tasks.forEach {
            TaskCardSection(it)
        }
    }
}

internal fun selectColor(status: Status): Triple<Color, Color, Color> {
    return when (status) {
        Status.TODO -> Triple(
            ColorPalette.BoxHeader.Todo,
            ColorPalette.BoxBody.Todo,
            ColorPalette.Border.Todo,
        )
        Status.INPROGRESS -> Triple(
            ColorPalette.BoxHeader.InProgress,
            ColorPalette.BoxBody.InProgress,
            ColorPalette.Border.InProgress,
        )
        Status.DONE -> Triple(
            ColorPalette.BoxHeader.Done,
            ColorPalette.BoxBody.Done,
            ColorPalette.Border.Done,
        )
    }
}
