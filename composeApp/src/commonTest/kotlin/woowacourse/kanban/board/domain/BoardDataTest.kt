package woowacourse.kanban.board.domain

import kotlin.test.Test
import kotlin.test.assertEquals

private class BoardData {
    private val cardList: MutableList<CardData> = mutableListOf()

    fun addCard(cardData: CardData) {
        cardList.add(cardData)
    }

    fun countAllCard(): Int {
        return cardList.count()
    }

    fun countCardsByState(state: TaskState): Int {
        return cardList.count {
            it.state == state
        }
    }

    fun getCompleteRate(): Int {
        val numOfAllCard = countAllCard()
        val numOfDoneCards = countCardsByState(TaskState.DONE)

        if (numOfAllCard == 0) return 0
        return (numOfDoneCards * 100 / numOfAllCard)
    }
}

class BoardDataTest {
    @Test
    fun `카드데이터 리스트를 받아서, 모든 카드의 개수를 셀 수 있다`() {
        // given
        val board = BoardData()
        board.addCard(CardData.create(title = "제목", state = TaskState.TO_DO, managerName = "별터"))
        board.addCard(CardData.create(title = "제목", state = TaskState.IN_PROGRESS, managerName = "별터"))
        val expected = 2

        // when
        val actual = board.countAllCard()

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun `카드데이터 리스트를 받아서, 상태별 카드의 개수를 셀 수 있다`() {
        // given
        val board = BoardData()
        board.addCard(CardData.create(title = "제목", state = TaskState.TO_DO, managerName = "별터"))
        board.addCard(CardData.create(title = "제목", state = TaskState.IN_PROGRESS, managerName = "별터"))
        board.addCard(CardData.create(title = "제목2", state = TaskState.IN_PROGRESS, managerName = "별터"))
        board.addCard(CardData.create(title = "제목", state = TaskState.DONE, managerName = "별터"))
        val toDoExpected = 1
        val inProgressExpected = 2
        val doneExpected = 1

        // when
        val toDoActual = board.countCardsByState(TaskState.TO_DO)
        val inProgressActual = board.countCardsByState(TaskState.IN_PROGRESS)
        val doneActual = board.countCardsByState(TaskState.DONE)

        // then
        assertEquals(toDoExpected, toDoActual)
        assertEquals(inProgressExpected, inProgressActual)
        assertEquals(doneExpected, doneActual)
    }

    @Test
    fun `카드데이터 리스트를 받아서, 완료율을 계산한다`() {
        // given
        val board = BoardData()
        board.addCard(CardData.create(title = "제목", state = TaskState.TO_DO, managerName = "별터"))
        board.addCard(CardData.create(title = "제목", state = TaskState.IN_PROGRESS, managerName = "별터"))
        board.addCard(CardData.create(title = "제목2", state = TaskState.IN_PROGRESS, managerName = "별터"))
        board.addCard(CardData.create(title = "제목", state = TaskState.DONE, managerName = "별터"))
        val expected = 25

        // when
        val actual = board.getCompleteRate()

        // then
        assertEquals(expected, actual)
    }
}
