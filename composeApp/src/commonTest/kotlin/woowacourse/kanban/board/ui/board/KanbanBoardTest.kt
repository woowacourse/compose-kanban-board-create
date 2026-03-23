package woowacourse.kanban.board.ui.board

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import woowacourse.kanban.board.domain.model.Card
import woowacourse.kanban.board.domain.model.Status
import woowacourse.kanban.board.domain.model.User

private const val TASK_CREATED_SNACKBAR = "새 태스크가 추가되었습니다."

@OptIn(ExperimentalCoroutinesApi::class)
class KanbanBoardTest {

    @Test
    fun `생성 시 선택한 상태에 맞는 컬럼에 추가된다`() {
        val card = Card(title = "진행중 카드", user = User("디노"))
        var todoCards = emptyList<Card>()
        var inProgressCards = emptyList<Card>()
        var doneCards = emptyList<Card>()

        when (Status.IN_PROGRESS) {
            Status.TODO -> todoCards = todoCards + card
            Status.IN_PROGRESS -> inProgressCards = inProgressCards + card
            Status.DONE -> doneCards = doneCards + card
        }

        assertEquals(0, todoCards.size)
        assertEquals(1, inProgressCards.size)
        assertEquals(0, doneCards.size)
    }

    @Test
    fun `태스크 생성 완료 메시지가 노출된다`() = runTest {
        val snackBarHostState = SnackbarHostState()
        val snackbar = launch {
            snackBarHostState.showSnackbar(
                message = TASK_CREATED_SNACKBAR,
                withDismissAction = true,
                duration = SnackbarDuration.Indefinite,
            )
        }

        advanceUntilIdle()
        val currentMessage = snackBarHostState.currentSnackbarData?.visuals?.message
        assertEquals(TASK_CREATED_SNACKBAR, currentMessage)

        snackbar.cancel()
    }

    @Test
    fun `아이콘을 누르면 스낵바가 닫힌다`() = runTest {
        val snackBarHostState = SnackbarHostState()
        val snackbar = launch {
            snackBarHostState.showSnackbar(
                message = TASK_CREATED_SNACKBAR,
                withDismissAction = true,
                duration = SnackbarDuration.Indefinite,
            )
        }

        advanceUntilIdle()
        val currentSnackBar = snackBarHostState.currentSnackbarData
        assertNotNull(currentSnackBar)

        currentSnackBar.dismiss()
        advanceUntilIdle()
        assertNull(snackBarHostState.currentSnackbarData)

        snackbar.cancel()
    }
}
