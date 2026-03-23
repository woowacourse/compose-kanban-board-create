package woowacourse.kanban.board.ui.taskCard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.Tag
import woowacourse.kanban.board.model.TaskCard

@Composable
fun TaskCardSection(taskCard: TaskCard) {
    TaskCardSection(
        title = taskCard.title,
        description = taskCard.description,
        tags = taskCard.tags,
        assignee = taskCard.assignee,
    )
}

@Composable
fun TaskCardSection(title: String, description: String, tags: Tag, assignee: Assignee) {
    Box(
        modifier = Modifier
            .border(
                border = BorderStroke(1.dp, Color.LightGray),
                shape = RoundedCornerShape(8.dp),
            )
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
    ) {
        Column {
            TitleSection(title = title)
            if (!description.isBlank()) {
                DescriptionSection(description = description)
            }
            if (!tags.isEmpty()) {
                TagGroupSection(tags = tags)
            }
            HorizontalDivider(modifier = Modifier.background(Color.Gray))
            AssigneeSection(assignee = assignee)
        }
    }
}
