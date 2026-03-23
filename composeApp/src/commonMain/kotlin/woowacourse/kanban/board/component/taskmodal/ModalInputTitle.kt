package woowacourse.kanban.board.component.taskmodal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ModalInputTitle(
    title: String,
    essential: Boolean,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "$title${if (essential) " *" else ""}",
        fontSize = 20.sp,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun ModalInputTitlePreview() {
    Column(
        modifier = Modifier.padding(5.dp),
    ) {
        ModalInputTitle(
            title = "제목",
            essential = true,
        )

        ModalInputTitle(
            title = "설명",
            essential = false,
        )
    }
}
