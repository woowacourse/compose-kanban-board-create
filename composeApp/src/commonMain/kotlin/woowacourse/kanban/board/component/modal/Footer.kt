package woowacourse.kanban.board.component.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.Blue50
import woowacourse.kanban.board.ComponentText
import woowacourse.kanban.board.Gray20

@Preview(showBackground = true)
@Composable
private fun FooterPreview() {
    Footer(true)
}

@Composable
fun Footer(
    isButtonEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
    ) {
        HorizontalDivider()
        Spacer(modifier = modifier.height(8.dp))
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            FooterButton(
                containerColor = Color.Transparent,
                contentColor = Gray20,
                text = ComponentText.CANCEL_BUTTON,
            )

            Spacer(modifier = modifier.width(12.dp))

            FooterButton(
                enabled = isButtonEnabled,
                containerColor = Blue50,
                text = ComponentText.CREATE_BUTTON,
            )
        }
    }
}
