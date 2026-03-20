package woowacourse.kanban.board.domain

class BoardData {
    private val cards: MutableList<CardData> = mutableListOf()

    fun getAllCard() = cards

    fun addCard(cardData: CardData) = cards.add(cardData)

    fun countAllCard(): Int = cards.count()

    fun countCardsByState(state: TaskState): Int = cards.count { it.state == state }

    fun getCompleteRate(): Float {
        val numOfAllCard = countAllCard()
        val numOfDoneCards = countCardsByState(TaskState.DONE)

        if (numOfAllCard == 0) return 0f
        return (numOfDoneCards.toFloat() / numOfAllCard.toFloat())
    }
}
