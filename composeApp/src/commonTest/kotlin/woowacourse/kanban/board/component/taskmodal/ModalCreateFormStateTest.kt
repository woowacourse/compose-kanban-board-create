package woowacourse.kanban.board.component.taskmodal

import woowacourse.kanban.board.model.TagError
import woowacourse.kanban.board.model.TitleError
import kotlin.test.Test
import kotlin.test.assertEquals

class ModalCreateFormStateTest {

    @Test
    fun `빈 제목은 유효하지 않다`() {
        val state = ModalCreateFormState()
        state.title = ""
        state.updateTitleValidation()
        assertEquals(
            state.titleError,
            TitleError.TITLE_FORM_INVALID,
        )
    }

    @Test
    fun `제목이 비어있지 않으면 유효하다`() {
        val state = ModalCreateFormState()
        state.title = "태스크 제목"
        state.updateTitleValidation()
        assertEquals(
            state.titleError,
            null,
        )
    }

    @Test
    fun `태그가 비어있으면 유효하다`() {
        val state = ModalCreateFormState()
        state.tag = ""
        state.updateTagValidation()
        assertEquals(
            state.tagError,
            null,
        )
    }

    @Test
    fun `태그가 5개 이하이고 각각 5자 이하이면 유효하다`() {
        val state = ModalCreateFormState()
        state.tag = "태그1,태그2,태그3,태그4,태그5"
        state.updateTagValidation()
        assertEquals(
            state.tagError,
            null,
        )
    }

    @Test
    fun `태그가 5개를 초과하면 유효하지 않다`() {
        val state = ModalCreateFormState()
        state.tag = "1,2,3,4,5,6"
        state.updateTagValidation()
        assertEquals(
            state.tagError,
            TagError.TAG_OVER_N,
        )
    }

    @Test
    fun `태그 중 하나라도 5자를 초과하면 유효하지 않다`() {
        val state = ModalCreateFormState()
        state.tag = "정상태그,육글자태그임"
        state.updateTagValidation()
        assertEquals(
            state.tagError,
            TagError.TAG_OVER_N,
        )
    }

    @Test
    fun `태그 형식이 올바르지 않으면 유효하지 않다 (쉼표만 있는 경우)`() {
        val state = ModalCreateFormState()
        state.tag = "태그1,,태그2"
        state.updateTagValidation()
        assertEquals(
            state.tagError,
            TagError.TAG_FORM_INVALID,
        )
    }
}
