package woowacourse.kanban.board.ui.taskCard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.model.Tag
import woowacourse.kanban.board.model.TagGroup

@Composable
fun TagGroupSection(
    tagGroup: TagGroup,
    modifier: Modifier = Modifier,
    ) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(8.dp),
    ) {
        tagGroup.tags.forEach {
            TagSection(tag = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TagGroupSectionPreview() {
    TagGroupSection(TagGroup(listOf(Tag("컴포넌트"), Tag("성능"))))
}

