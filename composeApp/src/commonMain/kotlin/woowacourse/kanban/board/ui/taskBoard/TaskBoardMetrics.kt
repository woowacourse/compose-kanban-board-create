package woowacourse.kanban.board.ui.taskBoard


fun calculateProgress(doneCount:Int, totalCount: Int): Float {
    if (totalCount == 0) {
        return 0.0f
    }
    return doneCount.toFloat() / totalCount.toFloat()
}

