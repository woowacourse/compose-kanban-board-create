package woowacourse.kanban.create.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CoachSelector(
    modifier: Modifier = Modifier,
    coaches: List<String>,
    selectedIndex: Int,
    onSelectChange: (Int) -> Unit,
) {
    Column(modifier) {
        HeaderText(title = "담당자 *")
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(coaches.size) { index ->
                CoachButton(
                    name = coaches[index],
                    isSelected = selectedIndex == index,
                    onClick = { onSelectChange(index) },
                    index = index,
                )
            }
        }
    }
}
