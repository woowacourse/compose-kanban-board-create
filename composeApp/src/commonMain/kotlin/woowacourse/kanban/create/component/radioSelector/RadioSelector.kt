package woowacourse.kanban.create.component.radioSelector

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.kanban.create.component.HeaderText

@Composable
fun RadioSelector(
    modifier: Modifier = Modifier,
    header: String,
    items: List<String>,
    itemContent: @Composable (index: Int) -> Unit,
) {

    Column(modifier) {
        HeaderText(title = header)
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(
                items.size,
            ) { index ->
                itemContent(index)
            }
        }
    }
}
