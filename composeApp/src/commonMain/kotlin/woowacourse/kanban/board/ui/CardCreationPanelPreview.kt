package woowacourse.kanban.board.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, name = "태스크 카드 생성창 뷰", device = Devices.DESKTOP)
@Composable
private fun CardCreationPanelPreview() {
    val uiState = rememberCardCreationState()

    CardCreationPanelContent(
        modifier = Modifier,
        uiState = uiState,
        onCloseClick = {},
        onCreateClick = {}
    )
}
