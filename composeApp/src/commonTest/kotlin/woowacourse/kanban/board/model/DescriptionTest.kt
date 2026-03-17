package woowacourse.kanban.board.model

import woowacourse.kanban.board.model.modal.Description
import kotlin.test.Test

class DescriptionTest {

    @Test
    fun `Description에 빈 값이 들어오면 Description이 생성된다`() {
        Description(value = "")
    }

    @Test
    fun `Description에 문자가 들어오면 Description이 생성된다`() {
        Description(value = "ㅇㅁㄴㅇㄴㅁㅇㅁㄴㅇㅁㄴ")
    }
}