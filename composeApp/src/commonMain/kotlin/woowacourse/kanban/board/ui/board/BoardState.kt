package woowacourse.kanban.board.ui.board

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.kanban.board.domain.BoardData
import woowacourse.kanban.board.domain.CardData
import woowacourse.kanban.board.domain.TaskState
import woowacourse.kanban.board.ui.card.CardUiState
import woowacourse.kanban.board.ui.card.toUiState

class BoardState(private val boardData: BoardData = BoardData()) {
    var showCardCreationPanel by mutableStateOf(false)
    val snackbarHostState = SnackbarHostState()
    var uiCards = mutableStateListOf<CardUiState>()
    var completeRate by mutableIntStateOf(boardData.getCompleteRate())
    var countOfDoneCards by mutableIntStateOf(boardData.countCardsByState(TaskState.DONE))
    var countOfAllCard by mutableIntStateOf(boardData.countAllCard())

    fun refreshUiCards() {
        val newUiCards = boardData.getAllCard().map { it.toUiState() }
        uiCards.clear()
        uiCards.addAll(newUiCards)

        completeRate = boardData.getCompleteRate()
        countOfDoneCards = boardData.countCardsByState(TaskState.DONE)
        countOfAllCard = boardData.countAllCard()

    }

    fun createCard(cardData: CardData) {
        boardData.addCard(cardData)
        refreshUiCards()
    }

    fun cardsByState(state: TaskState): List<CardUiState> = uiCards.filter { it.state == state }
}
