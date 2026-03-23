package woowacourse.kanban.board.fixture

import woowacourse.kanban.board.model.Assignee
import woowacourse.kanban.board.model.Status
import woowacourse.kanban.board.model.Tag
import woowacourse.kanban.board.model.TaskCard

object TaskCardFixture {
    val DEFAULT_ASSIGNEE = Assignee("다이노")
    val DEFAULT_DESCRIPTION = "설명입니다"
    val DEFAULT_TAG = Tag(listOf("태그1", "태그2", "태그3", "태그4", "태그5"))
    val DEFAULT_TITLE = "제목입니다"
    fun create() : TaskCard {
        return TaskCard(
            title = "제목입니다",
            description = "설명입니다",
            status = Status.TODO,
            tags = Tag(listOf("태그1", "태그2")),
            assignee = Assignee("다이노"),
        )
    }
}
