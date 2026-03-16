package woowacourse.kanban.create.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.PRIMARY_BORDER
import woowacourse.kanban.PRIMARY_SUB_TEXT
import woowacourse.kanban.STATUS_BG_SELECTED
import woowacourse.kanban.STATUS_BORDER_SELECTED
import woowacourse.kanban.STATUS_TEXT_SELECTED

@Composable
fun StatusButton(
    modifier: Modifier = Modifier,
    status: String,
    isSelected: Boolean = false,
    onClick: () -> Unit,
    index: Int,
) {
    val selectedModifier =
        Modifier.border(
            width = 2.dp,
            color = Color(STATUS_BORDER_SELECTED),
            shape = RoundedCornerShape(10.dp),
        )
            .background(Color(STATUS_BG_SELECTED))


    val unSelectedModifier =
        Modifier.border(
            width = 2.dp,
            color = Color(PRIMARY_BORDER),
            shape = RoundedCornerShape(10.dp),
        )

    Box(
        modifier = modifier.then(
            if (isSelected)
                selectedModifier
            else
                unSelectedModifier,
        )
            .clickable(
                onClick = onClick,
            )
            .testTag(
                tag = if (isSelected) "selected$index" else "unselected$index",
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            status,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 14.dp),
            color = if (!isSelected) {
                Color(PRIMARY_SUB_TEXT)
            } else {
                Color(STATUS_TEXT_SELECTED)
            },

            )
    }
}
